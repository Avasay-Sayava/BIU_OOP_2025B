package main;

import collision.BallRemover;
import collision.BlockRemover;
import collision.Collidable;
import collision.ScoreTrackingListener;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import graphics.Ball;
import graphics.Block;
import graphics.Paddle;
import graphics.ScoreText;
import graphics.Sprite;
import graphics.Text;
import util.Constants;
import util.Counter;
import util.LineUtils;
import util.MathUtils;
import util.ValueMap;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The main class of the game for ass3.
 */
public class Ass5Game extends Game {
    private Paddle paddle;
    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Block background;
    private Block gameBorder;
    private final List<Ball> balls;
    private final List<Block> blocks;
    private final List<Block> rectangles;
    private final List<Line> collidables;
    private final Map<Sprite, Integer> comparator;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final BlockRemover blockRemover;
    private final ScoreTrackingListener scoreTracker;
    private final Text score;
    private Block deathBlock;
    private final BallRemover ballRemover;
    private static final int SCORE_PRIORITY = 6;
    private static final int BLOCK_PRIORITY = 5;
    private static final int RECT_PRIORITY = 4;
    private static final int BALL_PRIORITY = 2;
    private static final int PADDLE_PRIORITY = 3;
    private static final int BORDER_PRIORITY = 1;
    private static final int BACKGROUND_PRIORITY = 0;
    private static final int GAME_BORDER = 20;
    private static final Rectangle SCREEN_RECT = new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT);
    private static final Rectangle GAME_RECT = SCREEN_RECT.reduced(GAME_BORDER);
    private int gameOver = 0;

    /**
     * Constructor.
     */
    public Ass5Game() {
        super();
        this.comparator = new ValueMap<>();
        this.blocks = new ArrayList<>();
        this.rectangles = new ArrayList<>();
        this.collidables = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(2);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.scoreTracker = new ScoreTrackingListener(this.getScore());
        this.score = new ScoreText(new Point((double) Constants.WIDTH / 2 - 50, 20), getScore());
    }

    /**
     * @return the list of collidables
     */
    @Override
    public Comparator<Sprite> spriteComparator() {
        return (o1, o2) -> this.comparator.get(o1) - this.comparator.get(o2);
    }

    @Override
    public void removeSprite(Sprite s) {
        this.comparator.remove(s);
        super.removeSprite(s);
    }

    /**
     * This method is called when the game is running.
     */
    @Override
    public void update() {
        if (this.gameOver > 0) {
            this.gameOver++;
        }
        if (this.gameOver == 5) {
            if (this.remainingBlocks.getValue() == 0) {
                System.out.println("You Win!\nYour score is: " + getScore().getValue());
                this.getGui().getDialogManager().showInformationDialog("You Win Dialog", "You Win! Score: "
                        + getScore().getValue());
            } else {
                System.out.println("Game Over.\nYour score is: " + getScore().getValue());
                this.getGui().getDialogManager().showInformationDialog("Game Over Dialog", "Game Over. Score: "
                        + getScore().getValue());
            }
            stop();
        }
        super.update();
        int sign = this.paddle.right() < GAME_RECT.left() ? 1 : this.paddle.left() > GAME_RECT.right() ? -1 : 0;
        this.paddle.transform(sign * GAME_RECT.getWidth(), 0);
        this.rightPaddle.transform(sign * GAME_RECT.getWidth(), 0);
        this.leftPaddle.transform(sign * GAME_RECT.getWidth(), 0);
        for (Ball ball : this.balls) {
            if (!this.paddle.doesntContain(ball)
                    || !this.rightPaddle.doesntContain(ball)
                    || !this.leftPaddle.doesntContain(ball)) {
                ball.setY(this.paddle.top() - Constants.BALL_RADIUS);
                ball.getCollider().collide(this.paddle);
                ball.getCollider().apply();
                ball.getCollider().collide(new Line(SCREEN_RECT.bottomLeft(), SCREEN_RECT.bottomRight()));
                ball.getCollider().apply();
            }
        }
    }

    /**
     * This method is called when the game is created.
     */
    @Override
    public void createAll() {
        this.background = new Block(SCREEN_RECT, Color.GRAY);
        this.comparator.put(this.background, BACKGROUND_PRIORITY);
        this.gameBorder = new Block(GAME_RECT, Color.getHSBColor(0.11f, 0.78f, 0.92f));
        this.comparator.put(this.gameBorder, BORDER_PRIORITY);
        this.paddle = new Paddle(new Point((Constants.WIDTH - Constants.PADDLE_WIDTH) / 2.0,
                Constants.HEIGHT - GAME_BORDER - Constants.PADDLE_HEIGHT),
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT,
                Color.YELLOW, this.getGui().getKeyboardSensor());
        this.comparator.put(this.paddle, PADDLE_PRIORITY);
        this.rightPaddle = new Paddle(this.paddle);
        this.rightPaddle.transform(GAME_RECT.getWidth(), 0);
        this.comparator.put(this.rightPaddle, PADDLE_PRIORITY);
        this.leftPaddle = new Paddle(this.paddle);
        this.leftPaddle.transform(-GAME_RECT.getWidth(), 0);
        this.comparator.put(this.leftPaddle, PADDLE_PRIORITY);
        this.balls.add(new Ball(Constants.WIDTH / 2.0 - Constants.BALL_RADIUS * 2 + 0.1,
                this.paddle.top() - Constants.BALL_RADIUS,
                Constants.BALL_RADIUS, Color.WHITE));
        this.balls.add(new Ball(Constants.WIDTH / 2.0 + Constants.BALL_RADIUS * 2 + 0.1,
                this.paddle.top() - Constants.BALL_RADIUS,
                Constants.BALL_RADIUS, Color.WHITE));
        for (Ball ball : this.balls) {
            this.comparator.put(ball, BALL_PRIORITY);
            ball.setVelocity(0, Constants.BALL_SPEED);
        }
        int rows = (int) (GAME_RECT.getHeight() / Constants.BLOCK_HEIGHT);
        int cols = (int) (GAME_RECT.getWidth() / Constants.BLOCK_WIDTH);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color color = Color.getHSBColor(
                        (float) MathUtils.randomDouble(j, j + 1) * rows / (rows * cols), 1, 0.9f);
                if (i <= j && i > 6 && i < 13) {
                    Block block =
                            new Block(new Point(j * Constants.BLOCK_WIDTH,
                                    i * Constants.BLOCK_HEIGHT).add(GAME_RECT.topLeft()),
                                    Constants.BLOCK_WIDTH,
                                    Constants.BLOCK_HEIGHT,
                                    color);
                    block.addHitListener(this.blockRemover);
                    block.addHitListener(this.scoreTracker);
                    this.blocks.add(block);
                    this.comparator.put(block, BLOCK_PRIORITY);
                    this.remainingBlocks.increase(1);
                }
            }
        }
        this.rectangles.add(new Block(SCREEN_RECT.topLeft(),
                new Point(GAME_RECT.left(), SCREEN_RECT.bottom()), Color.GRAY));
        this.rectangles.add(new Block(new Point(GAME_RECT.right(), SCREEN_RECT.top()),
                SCREEN_RECT.bottomRight(), Color.GRAY));
        for (Block block : this.rectangles) {
            this.comparator.put(block, RECT_PRIORITY);
        }
        this.deathBlock = new Block(GAME_RECT.bottomLeft(),
                new Point(GAME_RECT.right(), SCREEN_RECT.bottom()), Color.RED);
        this.deathBlock.addHitListener(this.ballRemover);
        recreateAll();
    }

    /**
     * This method is called to recreate all the blocks, paddles, and balls.
     */
    public void recreateAll() {
        this.addSprite(this.background);

        this.addSprite(this.gameBorder);
        for (Line line : this.gameBorder.getLines()) {
            this.addCollidable(line);
        }

        this.addCollidable(this.paddle);
        this.addSprite(this.paddle);

        this.addCollidable(this.rightPaddle);
        this.addSprite(this.rightPaddle);

        this.addCollidable(this.leftPaddle);
        this.addSprite(this.leftPaddle);

        for (Ball ball : this.balls) {
            this.addSprite(ball);
            this.addColliding(ball);
        }

        for (Block block : this.blocks) {
            this.addSprite(block);
            this.addCollidable(block);
        }

        for (Block block : this.rectangles) {
            this.addSprite(block);
        }

        this.addSprite(this.score);
        this.comparator.put(this.score, SCORE_PRIORITY);

        this.addCollidable(this.deathBlock);

        recalculateCollisions();
    }

    /**
     * Calculates the collisions for the game.
     */
    @Override
    public void recalculateCollisions() {
        for (Line line : this.collidables) {
            removeCollidable(line);
        }
        this.collidables.clear();
        List<Line> lines = new ArrayList<>();
        for (Block block : new ArrayList<>(this.blocks)) {
            for (int i = 0; i < block.getNumVertices(); i++) {
                Line line = block.getLines()[i];
                if (lines.stream().anyMatch(l -> l.equals(line))) {
                    lines.remove(lines.stream().filter(l -> l.equals(line))
                            .findAny().orElseThrow());
                } else {
                    lines.add(line);
                }
            }
        }
        this.collidables.addAll(LineUtils.mergeLines(lines));
        for (Line line : this.collidables) {
            addCollidable(line);
        }
    }

    /**
     * Finds a ball by its last center point.
     *
     * @param lastCenterPoint the last center point of the ball
     * @return an Optional containing the Ball if found, otherwise empty
     */
    public Optional<Ball> findBall(Point lastCenterPoint) {
        return this.balls.stream().filter(b -> b.getLastCenter().equals(lastCenterPoint)).findAny();
    }

    /**
     * Removes a collidable to the game.
     *
     * @param c the collidable to add
     */
    @Override
    public void removeCollidable(Collidable c) {
        super.removeCollidable(c);
        this.blocks.remove(c);
    }

    /**
     * Ends the game.
     */
    @Override
    public void end() {
        if (this.remainingBlocks.getValue() == 0) {
            getScore().increase(100);
        }
        this.gameOver = 1;
    }
}
