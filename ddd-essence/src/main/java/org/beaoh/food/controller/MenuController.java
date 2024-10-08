package org.beaoh.food.controller;

import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.Option;
import org.beaoh.food.domain.shop.OptionGroupId;
import org.beaoh.food.service.shop.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PutMapping("/menus/{menuId}/optionGroups/{optionGroupId}")
    public void changeOptionGroupName(@PathVariable("menuId") Long menuId,
                                      @PathVariable("optionGroupId") Long optionGroupId,
                                      OptionGroupNameChangeRequest request) {

        menuService.changeOptionGroupName(
                new MenuId(menuId),
                new OptionGroupId(optionGroupId),
                request.getName()
        );
    }

    @PutMapping("/menus/{menuId}/optionGroups/{optionGroupId}/options")
    public void changeOptionGroupName(@PathVariable("menuId") Long menuId,
                                      @PathVariable("optionGroupId") Long optionGroupId,
                                      @RequestBody OptionNameChangeRequest request) {
        menuService.changeOptionName(
                new MenuId(menuId),
                new OptionGroupId(optionGroupId),
                new Option(request.getCurrentName(), Money.wons(request.getCurrentPrice())),
                request.getNewName());
    }
}

class OptionGroupNameChangeRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class OptionNameChangeRequest {
    private String currentName;
    private Long currentPrice;
    private String newName;

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
