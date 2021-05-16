package com.example.diplom;

import com.example.diplom.model.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestObjects {

    static Logger logger = LoggerFactory.getLogger(MealControllerTests.class);

    public static final Meal TEST_0_MEAL = new Meal();
    public static final Meal TEST_1_MEAL = new Meal();
    public static final Meal TEST_2_MEAL = new Meal();
    public static final Meal TEST_3_MEAL = new Meal();
    public static final Meal TEST_4_MEAL = new Meal();
    public static final Meal TEST_5_MEAL = new Meal();

    public static final List<Meal> TEST_MEALS = new ArrayList<>();

    public static final Restaurant TEST_0_RESTAURANT = new Restaurant();
    public static final Restaurant TEST_1_RESTAURANT = new Restaurant();
    public static final Restaurant TEST_2_RESTAURANT = new Restaurant();

    public static final List<Restaurant> TEST_RESTAURANTS = new ArrayList<>();

    public static final Vote TEST_0_VOTE = new Vote();
    public static final Vote TEST_1_VOTE = new Vote();

    public static final List<Vote> TEST_VOTES = new ArrayList<>();

    public static final User TEST_0_USER = new User();
    public static final User TEST_1_USER = new User();
    public static final User TEST_2_USER = new User();

    public static final List<User> TEST_USERS = new ArrayList<>();

    public static final ItemMenu TEST_0_ITEM_MENU = new ItemMenu();
    public static final ItemMenu TEST_1_ITEM_MENU = new ItemMenu();
    public static final ItemMenu TEST_2_ITEM_MENU = new ItemMenu();
    public static final ItemMenu TEST_3_ITEM_MENU = new ItemMenu();
    public static final ItemMenu TEST_4_ITEM_MENU = new ItemMenu();
    public static final ItemMenu TEST_5_ITEM_MENU = new ItemMenu();

    public static final List<ItemMenu> TEST_ITEMS_MENU = new ArrayList<>();


    static {

        logger.info(()->"initial Test Objects");
        TEST_0_MEAL.setId(1);
        TEST_0_MEAL.setName("milk");
        TEST_0_MEAL.setDescription("fat white milk");
        TEST_0_MEAL.setPrice(100);

        TEST_1_MEAL.setId(2);
        TEST_1_MEAL.setName("ratatouille");
        TEST_1_MEAL.setDescription("wash your paws before cooking");
        TEST_1_MEAL.setPrice(50);

        TEST_2_MEAL.setId(3);
        TEST_2_MEAL.setName("pizza");
        TEST_2_MEAL.setDescription("Excellent pizza");
        TEST_2_MEAL.setPrice(0);

        TEST_3_MEAL.setId(4);
        TEST_3_MEAL.setName("water");
        TEST_3_MEAL.setDescription("just water");
        TEST_3_MEAL.setPrice(1);

        TEST_4_MEAL.setId(5);
        TEST_4_MEAL.setName("wiedzminskie eliksiry");
        TEST_4_MEAL.setDescription("dont eat it!");
        TEST_4_MEAL.setPrice(1000);

        TEST_5_MEAL.setId(6);
        TEST_5_MEAL.setName("bread");
        TEST_5_MEAL.setDescription("tasty crusty bread");
        TEST_5_MEAL.setPrice(200);

        TEST_MEALS.add(TEST_0_MEAL);
        TEST_MEALS.add(TEST_1_MEAL);
        TEST_MEALS.add(TEST_2_MEAL);
        TEST_MEALS.add(TEST_3_MEAL);
        TEST_MEALS.add(TEST_4_MEAL);
        TEST_MEALS.add(TEST_5_MEAL);

        TEST_0_RESTAURANT.setId(1);
        TEST_0_RESTAURANT.setName("The Three Broomsticks");
        TEST_0_RESTAURANT.setDescription("It so magick");
        TEST_0_RESTAURANT.setRating(3);

        TEST_1_RESTAURANT.setId(2);
        TEST_1_RESTAURANT.setName("The Mos Eisley Cantina");
        TEST_1_RESTAURANT.setDescription("Do. Or do not. There is no try");
        TEST_1_RESTAURANT.setRating(2);

        TEST_2_RESTAURANT.setId(3);
        TEST_2_RESTAURANT.setName("La Ratatouille");
        TEST_2_RESTAURANT.setDescription("It is Rat");
        TEST_2_RESTAURANT.setRating(4);

        TEST_RESTAURANTS.add(TEST_0_RESTAURANT);
        TEST_RESTAURANTS.add(TEST_1_RESTAURANT);
        TEST_RESTAURANTS.add(TEST_2_RESTAURANT);

        TEST_0_ITEM_MENU.setId(1);
        TEST_0_ITEM_MENU.setName("item");
        TEST_0_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_0_ITEM_MENU.setMeal(TEST_0_MEAL);
        TEST_0_ITEM_MENU.setRestaurant(TEST_0_RESTAURANT);

        TEST_1_ITEM_MENU.setId(2);
        TEST_1_ITEM_MENU.setName("item");
        TEST_1_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_1_ITEM_MENU.setMeal(TEST_1_MEAL);
        TEST_1_ITEM_MENU.setRestaurant(TEST_0_RESTAURANT);

        TEST_2_ITEM_MENU.setId(3);
        TEST_2_ITEM_MENU.setName("item");
        TEST_2_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_2_ITEM_MENU.setMeal(TEST_2_MEAL);
        TEST_2_ITEM_MENU.setRestaurant(TEST_1_RESTAURANT);

        TEST_3_ITEM_MENU.setId(4);
        TEST_3_ITEM_MENU.setName("item");
        TEST_3_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_3_ITEM_MENU.setMeal(TEST_3_MEAL);
        TEST_3_ITEM_MENU.setRestaurant(TEST_1_RESTAURANT);

        TEST_4_ITEM_MENU.setId(5);
        TEST_4_ITEM_MENU.setName("item");
        TEST_4_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_4_ITEM_MENU.setMeal(TEST_4_MEAL);
        TEST_4_ITEM_MENU.setRestaurant(TEST_2_RESTAURANT);

        TEST_5_ITEM_MENU.setId(6);
        TEST_5_ITEM_MENU.setName("item");
        TEST_5_ITEM_MENU.setAdded(LocalDate.parse("2021-05-15"));
        TEST_5_ITEM_MENU.setMeal(TEST_5_MEAL);
        TEST_5_ITEM_MENU.setRestaurant(TEST_2_RESTAURANT);

        TEST_ITEMS_MENU.add(TEST_0_ITEM_MENU);
        TEST_ITEMS_MENU.add(TEST_1_ITEM_MENU);
        TEST_ITEMS_MENU.add(TEST_2_ITEM_MENU);
        TEST_ITEMS_MENU.add(TEST_3_ITEM_MENU);
        TEST_ITEMS_MENU.add(TEST_4_ITEM_MENU);
        TEST_ITEMS_MENU.add(TEST_5_ITEM_MENU);

        TEST_0_USER.setId(1L);
        TEST_0_USER.setActive(true);
        TEST_0_USER.setName("admin");
        TEST_0_USER.setPassword("$2y$12$sCcExijql0/nks7tJh/cPOxvk1mwhPVZwpggPmX285tgO3tvnLcIm");
        TEST_0_USER.setRole(Set.of(Role.ADMIN, Role.USER));

        TEST_1_USER.setId(2L);
        TEST_1_USER.setActive(true);
        TEST_1_USER.setName("user");
        TEST_1_USER.setPassword("$2y$12$OLJPuWKFtd/5VkE6faUxAuYkLl1DcT8nKY2u2Scyt6KPp3M3KBNPS");
        TEST_1_USER.setRole(Set.of(Role.USER));

        TEST_2_USER.setId(3L);
        TEST_2_USER.setActive(true);
        TEST_2_USER.setName("notuser");
        TEST_2_USER.setPassword("$2y$12$N4KgCR4zwzBevxVObUjKUurfSTYDec2k6SsBaqpAXM5VyKg1V4TXS");
        TEST_2_USER.setRole(Set.of());

        TEST_USERS.add(TEST_0_USER);
        TEST_USERS.add(TEST_1_USER);
        TEST_USERS.add(TEST_2_USER);

        TEST_0_VOTE.setId(1);
        TEST_0_VOTE.setName("vote");
        TEST_0_VOTE.setVoited(LocalDateTime.parse("2021-05-15T09:11:10"));
        TEST_0_VOTE.setRestaurant(TEST_0_RESTAURANT);
        TEST_0_VOTE.setUser(TEST_0_USER);

        TEST_1_VOTE.setId(2);
        TEST_1_VOTE.setName("vote");
        TEST_1_VOTE.setVoited(LocalDateTime.parse("2021-05-15T09:11:10"));
        TEST_1_VOTE.setRestaurant(TEST_1_RESTAURANT);
        TEST_1_VOTE.setUser(TEST_1_USER);

        TEST_VOTES.add(TEST_0_VOTE);
        TEST_VOTES.add(TEST_1_VOTE);
    }

}
