package com.example.diplom;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.controllers.MealRestController;
import com.example.diplom.controllers.RestaurantRestController;
import com.example.diplom.controllers.UserRestController;
import com.example.diplom.controllers.VoteRestController;
import com.example.diplom.model.Meal;
import com.example.diplom.model.Restaurant;
import com.example.diplom.model.Vote;
import com.example.diplom.model.web.VoteTo;
import com.example.diplom.service.RestaurantService;
import com.example.diplom.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("classpath:data.sql")
class MealControllerTests {

    static final Logger logger = LoggerFactory.getLogger(MealControllerTests.class);

    static private MealRestController controller;

    @BeforeAll
    static void initializeService(@Autowired MealRestController controller){
        logger.info(()->"initialize test");
        MealControllerTests.controller = controller;
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getAllTest(){
        logger.info(()->"start getAllTest");
        List<Meal> mealList = controller.getAll();
        Assertions.assertArrayEquals(new List[]{TestObjects.TEST_MEALS}, new List[]{mealList});
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void createTest() {
        logger.info(()->"start createTest");
        Meal meal = new Meal();
        meal.setName("France bread");
        meal.setDescription("tasty bread");
        meal.setPrice(201);

        Meal meal_res = controller.create(meal);

        Assertions.assertNotNull(meal_res);
        Assertions.assertEquals(meal_res.getName(),"France bread");
        Assertions.assertEquals(meal_res.getDescription(),"tasty bread");
        Assertions.assertEquals(meal_res.getPrice(),201);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getTest(){
        logger.info(()->"start getTest");
        Meal meal_res = controller.get(1);
        Assertions.assertEquals(meal_res, TestObjects.TEST_0_MEAL);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void deleteTest(){
        logger.info(()->"start deleteTest");
        controller.delete(2);
        Assertions.assertThrows(NotFoundException.class, ()->controller.get(2));
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void updateTest(){
        logger.info(()->"start updateTest");
        Meal meal = new Meal();
        meal.setName("France bread1");
        meal.setDescription("tasty bread");
        meal.setPrice(201);

        controller.update(3, meal);

        Meal meal_res = controller.get(3);

        Assertions.assertNotNull(meal_res);
        Assertions.assertEquals(meal_res.getName(),"France bread1");
        Assertions.assertEquals(meal_res.getDescription(),"tasty bread");
        Assertions.assertEquals(meal_res.getPrice(),201);
    }

}

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("classpath:data.sql")
@RunWith(PowerMockRunner.class)
@PrepareForTest({ LocalDateTime.class })
class RestaurantControllerTests {

    static final Logger logger = LoggerFactory.getLogger(RestaurantControllerTests.class);

    private RestaurantRestController controller;

    @BeforeEach
    void initializeService(@Autowired RestaurantRestController controller){
        this.controller = controller;
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getAllTest(){
        logger.info(()->"start getAllTest");
        List<Restaurant> restaurantList = controller.getAll();
        Assertions.assertArrayEquals(new List[]{TestObjects.TEST_RESTAURANTS}, new List[]{restaurantList});
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void createTest() {
        logger.info(()->"start createTest");
        Restaurant restaurant = new Restaurant();
        restaurant.setName("France bread");
        restaurant.setDescription("tasty bread");
        restaurant.setRating(1);

        Restaurant restaurant_res = controller.create(restaurant);

        Assertions.assertNotNull(restaurant_res);
        Assertions.assertEquals(restaurant_res.getName(),"France bread");
        Assertions.assertEquals(restaurant_res.getDescription(),"tasty bread");
        Assertions.assertEquals(restaurant_res.getRating(),1);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getTest(){
        logger.info(()->"start getTest");
        Restaurant restaurant_res = controller.get(1);
        Assertions.assertEquals(TestObjects.TEST_0_RESTAURANT, restaurant_res);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void deleteTest(){
        logger.info(()->"start deleteTest");
        controller.delete(2);
        Assertions.assertThrows(NotFoundException.class, ()->controller.get(2));
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void updateTest() {
        logger.info(() -> "start updateTest");
        Restaurant restaurant = new Restaurant();
        restaurant.setName("France bread1");
        restaurant.setDescription("tasty bread");
        restaurant.setRating(1);

        controller.update(2, restaurant);

        Restaurant restaurant_res = controller.get(2);

        Assertions.assertNotNull(restaurant_res);
        Assertions.assertEquals(restaurant_res.getName(), "France bread1");
        Assertions.assertEquals(restaurant_res.getDescription(), "tasty bread");
        Assertions.assertEquals(restaurant_res.getRating(), 1);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    @Disabled
    void voteTest(){

        String instantExpected = "2014-12-22T18:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        LocalDateTime dateTime = LocalDateTime.now(clock);

        PowerMockito.mockStatic(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now(clock)).thenReturn(dateTime);

        VoteTo voteTo = controller.vote(2);

        Assertions.assertNotNull(voteTo);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void menuGetAllTest() {
        Assertions.assertEquals(2, controller.menuGetAll(1).size());
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void menuAddTest() {
        controller.menuAdd(3,1);
        Assertions.assertEquals(3, controller.menuGetAll(3).size());
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void menuDeleteByIdTest() {
        controller.menuDeleteById(1);
        Assertions.assertEquals(1, controller.menuGetAll(1).size());
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void menuDeleteAllTest() {
        controller.menuDeleteAll(2);
        Assertions.assertEquals(0, controller.menuGetAll(2).size());
    }


}

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Disabled
@Sql("classpath:data.sql")
class UserControllerTests {

    private UserRestController controller;

    @BeforeEach
    void initializeService(@Autowired UserRestController controller){
        this.controller = controller;
    }

}

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("classpath:data.sql")
class VoteControllerTests {

    private VoteRestController controller;
    private RestaurantService restaurantService;
    private UserService userService;

    @BeforeEach
    void initializeService(@Autowired VoteRestController controller,
                           @Autowired RestaurantService restaurantService,
                           @Autowired UserService userService){
        this.controller = controller;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getAllTest(){
        List<Vote> voteList = controller.getAll();
        Assertions.assertArrayEquals(new List[]{TestObjects.TEST_VOTES}, new List[]{voteList});
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void deleteTest(){
        controller.delete(1);
        Assertions.assertThrows(NotFoundException.class, ()->controller.get(1));
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void createTest() {
        Vote vote = new Vote();
        vote.setName("vote");
        vote.setVoited(LocalDateTime.parse("2021-05-15T09:11:10"));
        vote.setRestaurant(restaurantService.get(1));
        vote.setUser(userService.get(1));

        Vote vote_res = controller.create(vote);

        Assertions.assertNotNull(vote_res);
        Assertions.assertEquals(vote_res.getRestaurant(),restaurantService.get(1));
        Assertions.assertEquals(vote_res.getUser().getName(),userService.get(1).getName());
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getTest(){
        Vote vote_res = controller.get(1);
        Assertions.assertEquals(vote_res, TestObjects.TEST_0_VOTE);
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void updateTest(){
        Vote vote = new Vote();
        vote.setName("vote");
        vote.setVoited(LocalDateTime.parse("2021-05-15T09:11:10"));
        vote.setRestaurant(restaurantService.get(2));
        vote.setUser(userService.get(1));

        controller.update(1, vote);

        Vote vote_res = controller.get(1);

        Assertions.assertNotNull(vote_res);
        Assertions.assertEquals(vote_res.getRestaurant(),restaurantService.get(2));
        Assertions.assertEquals(vote_res.getUser().getName(),userService.get(1).getName());
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void getStatisticTest(){
        Assertions.assertEquals(1, controller.getStatistics().get(restaurantService.get(2)));
        Assertions.assertEquals(1, controller.getStatistics().get(restaurantService.get(1)));
    }

    @Test
    @WithMockUser(username="admin", authorities = {"USER","ADMIN"})
    void deleteAllTest(){
        controller.deleteAll();
        Assertions.assertEquals(0, controller.getAll().size());
    }


}
