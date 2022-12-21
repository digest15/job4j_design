package ru.job4j.arhitecture.lsp.examples.render;

import ru.job4j.arhitecture.lsp.examples.render.entity.JpgImage;

/**
 * Example 4
 * Этот класс нарушает принцип подстановки Лисков:
 * 1. Усиливает постусловие - за счет ковариантности возвращаемого значения метода
 * 2. Нарушает инвариант - переопределяет тип свойства
 */
public class JpgRander extends Render {
    protected JpgImage image;

    public JpgRander(JpgImage image) {
        super(image);
        this.image = image;
    }

    @Override
    public JpgImage render() {
        return image;
    }
}
