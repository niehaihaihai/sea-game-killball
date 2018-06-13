package com.sea.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sea on 2018/6/8.
 */

public class StageMuchBall extends Stage {

    private int WORLD_WIDTH = 192;
    private int WORLD_HEIGHT = 108;

    private World world;
    private Texture ball2;
    private Batch batch;
    private List<Body> bodies = new ArrayList<Body>();
    private Body circleBody1;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;

    public StageMuchBall(Viewport viewport) {
        setViewport(viewport);
        batch = new SpriteBatch();
        ball2 = new Texture(Gdx.files.internal("ball.png"));

        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true); // 标准重力场

//        addEdge(new Vector2(0, 5), new Vector2(1915, 5));
//        addEdge(new Vector2(0, 5), new Vector2(0, 1080));
//        addEdge(new Vector2(0, 1080), new Vector2(1915, 1080));
//        addEdge(new Vector2(1915, 5), new Vector2(1915, 1080));

        addEdge(new Vector2(0, 1), new Vector2(WORLD_WIDTH-1, 1));
        addEdge(new Vector2(0, 1), new Vector2(0, WORLD_HEIGHT));
        addEdge(new Vector2(0, WORLD_HEIGHT), new Vector2(WORLD_WIDTH-1, WORLD_HEIGHT));
        addEdge(new Vector2(WORLD_WIDTH-1, 1), new Vector2(WORLD_WIDTH-1, WORLD_HEIGHT));

        BodyDef bd2 = new BodyDef();
        bd2.position.set(50, 50);
        bd2.type = BodyDef.BodyType.DynamicBody;
        circleBody1 = world.createBody(bd2);
        CircleShape circleShape2 = new CircleShape();// 创建一个圆
        circleShape2.setRadius(4); // 设置半径
        FixtureDef ballShapeDef2 = new FixtureDef();
        ballShapeDef2.shape = circleShape2;
        ballShapeDef2.density = 0.1f;
        ballShapeDef2.friction = 0f;
        ballShapeDef2.restitution = 1.0f;
        circleBody1.createFixture(ballShapeDef2);
        Vector2 force2 = new Vector2(50f, 50f);
        circleBody1.applyLinearImpulse(force2, bd2.position, false);

        int min=1;
        int max=99;
        Random random = new Random();
        for(int i= 0;i<100;i++) {
            addBall(random.nextInt(max)%(max-min+1) + min, random.nextInt(max)%(max-min+1) + min);
        }

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
    }

    private void addBall(float x, float y) {
        BodyDef bd = new BodyDef();
        bd.position.set(x, y);
        bd.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bd);
        CircleShape circleShape = new CircleShape();// 创建一个圆
        circleShape.setRadius(4); // 设置半径
        FixtureDef ballShapeDef = new FixtureDef();
        ballShapeDef.shape = circleShape;
        ballShapeDef.density = 0.1f;
        ballShapeDef.friction = 0f;
        ballShapeDef.restitution = 1.0f;
        body.createFixture(ballShapeDef);
        Vector2 force = new Vector2(50f, 50f);
        body.applyLinearImpulse(force, bd.position, false);
        bodies.add(body);
    }

    private void addEdge(Vector2 pos1, Vector2 pos2) {
        EdgeShape edgeShape = new EdgeShape();// 创建一个边
        edgeShape.set(pos1, pos2);// 设置2点
        addShape(edgeShape, new Vector2(0, 0), BodyDef.BodyType.StaticBody);
    }

    public void addShape(Shape shap, Vector2 pos, BodyDef.BodyType type) {
        BodyDef bd = new BodyDef();
        bd.position.set(pos.x, pos.y);
        bd.type = type;
        Body b = world.createBody(bd);
        b.createFixture(shap, 1f);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        world.step(1/60f, 6, 2);
//        box2DDebugRenderer.render(world, camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(int i=0; i<bodies.size(); i++) {
            batch.draw(ball2, bodies.get(i).getPosition().x-4, bodies.get(i).getPosition().y-4, 8,8);
        }
        batch.draw(ball2, circleBody1.getPosition().x-4, circleBody1.getPosition().y-4, 8,8);
        batch.end();
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }
}
