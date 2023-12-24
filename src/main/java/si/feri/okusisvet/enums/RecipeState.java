package si.feri.okusisvet.enums;

import lombok.Getter;

public enum RecipeState {
    PRIVATE_DRAFT(false),
    PUBLIC_PUBLISHED(true),
    PRIVATE_COPY(false);
    @Getter
    private final boolean showInPublicList;

    RecipeState(boolean showInPublicList) {
        this.showInPublicList = showInPublicList;
    }
}
