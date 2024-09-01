package org.beaoh.food.service.cart;

import java.util.ArrayList;
import java.util.List;
import org.beaoh.food.domain.cart.CartLineItem;
import org.beaoh.food.domain.cart.CartOption;
import org.beaoh.food.domain.cart.CartOptionGroup;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuRepository;
import org.beaoh.food.domain.shop.Option;
import org.beaoh.food.domain.shop.OptionGroup;
import org.beaoh.food.service.cart.CartLineItemRequest.CartOptionGroupRequest;
import org.beaoh.food.service.cart.CartLineItemRequest.CartOptionRequest;
import org.springframework.stereotype.Component;

@Component
public class CartLineItemMapper {

    private final MenuRepository menuRepository;


    public CartLineItemMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public CartLineItem map(CartLineItemRequest request) {
        Menu menu = menuRepository.find(request.getMenuId());

        CartLineItem cartLineItem = new CartLineItem(menu.getId(), menu.getName(), request.getCount());

        for(CartOptionGroupRequest optionGroupRequest : request.getOptionGroupRequests()) {
            OptionGroup optionGroup = menu.getOptionGroup(optionGroupRequest.getOptionGroupId())
                    .orElseThrow(IllegalArgumentException::new);

            List<CartOption> cartOptions = new ArrayList<>();
            for (CartOptionRequest optionRequest : optionGroupRequest.getOptions()) {
                Option found = optionGroup.findOption(new Option(
                        optionRequest.getName(),
                        Money.wons(optionRequest.getPrice()))).orElseThrow(IllegalArgumentException::new);

                cartOptions.add(new CartOption(found.getName(), found.getPrice()));
            }

            cartLineItem.addCartOptionGroup(new CartOptionGroup(optionGroup.getName(), cartOptions));
        }

        return cartLineItem;
    }
}
