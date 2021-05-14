package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.*;
import com.example.diplom.model.web.VoteTo;
import com.example.diplom.repository.RestaurantRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RestaurantService  implements IService<Restaurant>{

    private static final LocalTime END_VOTING = LocalTime.of(11,00);

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
    public Restaurant get(int id) throws NotFoundException {
        Restaurant obj = repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Restaurant not found by id: %d", id)));
        return obj;
    }

    @Override
    public List<Restaurant> getAll() {
        return (List<Restaurant>) repo.findAll();
    }

    public ItemMenu menuAdd(int idRestaurant, int idMeal) {
        ItemMenu item = new ItemMenu();
        item.setAdded(LocalDate.now());
        item.setName("item");
        item.setRestaurant(get(idRestaurant));
        item.setMeal(mealService.get(idMeal));
        return itemMenuService.create(item);
    }

    public List<Meal> menuGetAll(int id) {
        return itemMenuService.getByRestaurant(get(id));
    }

    public void menuDeleteById(int id){
        itemMenuService.delete(id);
    }

    public void menuDeleteAll(int id){
        itemMenuService.deleteAllByRestaurant(get(id));
    }

    public VoteTo vote(int id){
        LocalDateTime date = LocalDateTime.now();
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
        obj.setId(null);
        return repo.save(obj);
    }

    @Override
    public Restaurant update(int id, Restaurant obj) {
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
