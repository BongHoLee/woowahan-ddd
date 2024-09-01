package org.beaoh.food.service.cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.beaoh.food.domain.cart.CartId;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.OptionGroupId;
import org.beaoh.food.domain.shop.ShopId;

@Data
public class CartLineItemRequest {
    private ShopId shopId;
    private CartId cartId;
    private MenuId menuId;
    private Integer count;
    private List<CartOptionGroupRequest> optionGroupRequests;

    public CartLineItemRequest() {
    }

    public CartLineItemRequest(ShopId shopId, CartId cartId, MenuId menuId, Integer count, CartOptionGroupRequest ... optionGroupRequests) {
        this.shopId = shopId;
        this.cartId = cartId;
        this.menuId = menuId;
        this.count = count;
        this.optionGroupRequests = Arrays.asList(optionGroupRequests);
    }

    @Data
    public static class CartOptionGroupRequest {
        private OptionGroupId optionGroupId;
        private Integer count;
        private List<CartOptionRequest> options = new ArrayList<>();

        public CartOptionGroupRequest() {
        }

        public CartOptionGroupRequest(OptionGroupId optionGroupId, Integer count, List<CartOptionRequest> options) {
            this.optionGroupId = optionGroupId;
            this.count = count;
            this.options = options;
        }
    }

    @Data
    public static class CartOptionRequest {
        private String name;
        private Long price;

        public CartOptionRequest() {}

        public CartOptionRequest(String name, Long price) {
            this.name = name;
            this.price = price;
        }
    }
}
