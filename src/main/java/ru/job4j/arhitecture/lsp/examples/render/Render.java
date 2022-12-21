package ru.job4j.arhitecture.lsp.examples.render;

import ru.job4j.arhitecture.lsp.examples.render.entity.Image;

public class Render {
    protected Image image;

    public Render(Image image) {
        this.image = image;
    }

    public Image render() {
        return image;
    }
}
