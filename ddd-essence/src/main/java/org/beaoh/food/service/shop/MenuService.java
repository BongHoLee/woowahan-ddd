package org.beaoh.food.service.shop;

import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.MenuRepository;
import org.beaoh.food.domain.shop.Option;
import org.beaoh.food.domain.shop.OptionGroupId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {
    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public void changeOptionGroupName(MenuId menuId, OptionGroupId optionGroupId, String name) {
        Menu menu = menuRepository.find(menuId);
        menu.changeOptionGroupName(optionGroupId, name);
    }

    @Transactional
    public void changeOptionName(MenuId menuId, OptionGroupId optionGroupId, Option target, String optionName) {
        Menu menu = menuRepository.find(menuId);
        menu.changeOptionName(optionGroupId, target, optionName);
    }
}
