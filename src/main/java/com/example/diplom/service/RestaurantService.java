package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.*;
import com.example.diplom.model.web.VoteTo;
import com.example.diplom.repository.RestaurantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService  implements IService<Restaurant>{

    static final Logger logger = LoggerFactory.getLogger(ItemMenuService.class);

    @Autowired
    private Clock clock;

    private static final LocalTime END_VOTING = LocalTime.of(11, 0);

    private final RestaurantRepo repo;
    private final UserService userService;
    private final VoteService voteService;
    private final ItemMenuService itemMenuService;
    private final MealService mealService;

    public RestaurantService(RestaurantRepo repo, VoteService service, UserService userService, VoteService voteService, ItemMenuService itemMenuService, MealService mealService) {
        this.repo = repo;
        this.userService = userService;
        this.voteService = voteService;
        this.itemMenuService = itemMenuService;
        this.mealService = mealService;
    }

    @Override
    @Cacheable("restaurants")
    public Restaurant get(int id) throws NotFoundException {
        logger.info(String.format("get with id: %s", id));
        return repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Restaurant not found by id: %d", id)));
    }

    @Override
    public List<Restaurant> getAll() {
        logger.info("get All Restaurants");
        return ((List<Restaurant>) repo.findAll()).stream()
                .sorted(Comparator.comparingInt(Restaurant::getId))
                .collect(Collectors.toList());
    }

    public ItemMenu menuAdd(int idRestaurant, int idMeal) {
        logger.info(String.format("Add to restaurant with id %s meal with id %s", idRestaurant, idMeal));
        ItemMenu item = new ItemMenu();
        item.setAdded(LocalDate.now());
        item.setName("item");
        item.setRestaurant(get(idRestaurant));
        item.setMeal(mealService.get(idMeal));
        return itemMenuService.create(item);
    }

    public List<Meal> menuGetAll(int id) {
        logger.info(String.format("get All Meals by Restaurant with id = %s", id));
        return itemMenuService.getByRestaurant(get(id));
    }

    public void menuDeleteById(int id){
        logger.info(String.format("delete MealItems by id = %s", id));
        itemMenuService.delete(id);
    }

    public void menuDeleteAll(int id){
        logger.info("delete All MealItems by Restaurant");
        itemMenuService.deleteAllByRestaurant(get(id));
    }

    public VoteTo vote(int id){
        logger.info(String.format("Vote to restaurant with id = %s", id));
        LocalDateTime date = LocalDateTime.now(clock);
        if (date.toLocalTime().isAfter(END_VOTING)) {
            return null;
        }

        String nameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUserName(nameCurrentUser);
        Restaurant restaurant = get(id);

        Vote vote = voteService.getByUserOrCreate(user);
        vote.setRestaurant(restaurant);
        vote.setUser(user);
        vote.setVoited(date);
        vote.setName("v");

        return new VoteTo(voteService.create(vote));
    }

    @Override
    public Restaurant create(Restaurant obj) {
        logger.info(String.format("Create: %s", obj.toString()));
        obj.setId(null);
        return repo.save(obj);
    }

    @Override
    @CachePut("restaurants")
    public Restaurant update(int id, Restaurant obj) {
        logger.info(String.format("Update id=%s: %s", id, obj.toString()));
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    @CacheEvict("restaurants")
    public void delete(int id) {
        logger.info(String.format("delete by id: %s", id));
        repo.deleteById(id);
    }
}
