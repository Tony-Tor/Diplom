package com.example.diplom;

import com.example.diplom.controllers.MealRestController;
import com.example.diplom.model.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MealControllerTest {

    private static final Meal TEST0 = new Meal();
    private static final Meal TEST1 = new Meal();
    private static final Meal TEST2 = new Meal();
    private static final Meal TEST3 = new Meal();
    private static final Meal TEST4 = new Meal();
    private static final Meal TEST5 = new Meal();
    private static final Meal TEST6 = new Meal();
    private static final Meal TEST7 = new Meal();
    private static final Meal TEST8 = new Meal();
    private static final Meal TEST9 = new Meal();
    private static final Meal TEST10 = new Meal();
    private static final Meal TEST11 = new Meal();

    private static final Meal TEST0WRONG = new Meal();
    private static final Meal TEST1WRONG = new Meal();
    private static final Meal TEST2WRONG = new Meal();
    private static final Meal TEST3WRONG = new Meal();
    private static final Meal TEST4WRONG = new Meal();
    private static final Meal TEST5WRONG = new Meal();

    static {
        TEST0.setName("Bread");
        TEST0.setDescription("Tasty Bread");
        TEST0.setPrice(200);

        TEST0WRONG.setName(null);
        TEST0WRONG.setDescription("Tasty Bread");
        TEST0WRONG.setPrice(200);

        TEST1WRONG.setName("Bread");
        TEST1WRONG.setDescription("Tasty Bread");
        TEST1WRONG.setPrice(3000001);

        TEST2WRONG.setName("Bread");
        TEST2WRONG.setDescription("Tasty Bread");
        TEST2WRONG.setPrice(-1);
    }



    private final MealRestController mealRestController;

    public MealControllerTest(MealRestController mealRestController) {
        this.mealRestController = mealRestController;
    }

    @Test
    public void addMeals(){
        Assertions.assertEquals(mealRestController.create(
                new Meal()
        ));
    }
}
