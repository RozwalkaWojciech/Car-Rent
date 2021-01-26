package pl.rozwalka.rentcar.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.rozwalka.rentcar.app.service.AppService;
import pl.rozwalka.rentcar.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("app")
public class AppController {

    private final AppService appService;

    @PutMapping("{userId}/rent")
    public User rentCarByUser(@PathVariable Long userId,
                              @RequestParam Long carId) {
        return appService.rentCarByUser(userId, carId);
    }

    @PutMapping("{userId}/give-back")
    public User giveBackCarByUser(@PathVariable Long userId,
                                  @RequestParam Long carId) {
        return appService.giveBackCarByUser(userId, carId);
    }
}
