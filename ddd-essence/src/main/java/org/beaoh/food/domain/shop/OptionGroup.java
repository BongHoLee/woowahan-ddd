package org.beaoh.food.domain.shop;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.DomainEntity;
import org.beaoh.food.domain.shop.OptionGroupId.OptionGroupIdJavaType;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class OptionGroup extends DomainEntity<OptionGroup, OptionGroupId> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(OptionGroupIdJavaType.class)
    private OptionGroupId id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MANDATORY")
    private boolean mandatory;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "OPTION", joinColumns = @JoinColumn(name = "OPTION_GROUP_ID"))
    private List<Option> options = new ArrayList<>();

    public OptionGroup(String name, boolean mandatory, Option option) {
        this(null, name, mandatory, Arrays.asList(option));
    }

    protected OptionGroup() {}

    @Builder
    public OptionGroup(OptionGroupId id, String name, boolean mandatory, List<Option> options) {
        this.id = id;
        this.mandatory = mandatory;
        setName(name);
        setOptions(options);
    }

    private void setName(String name) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("옵션그룹명은 길이는 최소 2글자 이상이어야 합니다.");
        }

        this.name = name;
    }

    private void setOptions(List<Option> options) {
        if (options == null || options.size() < 1) {
            throw new IllegalArgumentException("옵션의 길이는 최소 1개 이상이어야 합니다.");
        }

        this.options = options;
    }

    public Optional<Option> findOption(Option target) {
        return options.stream().filter(option -> option.equals(target)).findFirst();
    }

    public boolean isFree() {
        return options.stream().allMatch(Option::isFree);
    }

    public void chaneName(String name) {
        this.name = name;
    }

    public void changeOptionName(Option target, String optionName) {
        Option option = options.stream()
                .filter(each -> each.equals(target))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        options.remove(option);
        options.add(target.changeName(optionName));
    }
}
