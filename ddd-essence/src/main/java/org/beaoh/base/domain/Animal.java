package org.beaoh.base.domain;

public abstract class Animal<T extends Animal<T>> {

    public boolean isSameType(T other) {
        return other.getClass().equals(getClass());
    }

    public abstract String makeSound();
}

class Dog extends Animal<Dog> {
    @Override
    public String makeSound() {
        return "Bark";
    }
}

class Cat extends Animal<Dog> {
    @Override
    public String makeSound() {
        return "Meow";
    }
}

class DDog extends Dog {
    @Override
    public String makeSound() {
        return "Bark Bark";
    }
}
