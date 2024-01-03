package si.feri.okusisvet.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum RecipeState {
    PRIVATE_DRAFT(false),
    PUBLIC_PUBLISHED(true),
    PRIVATE_COPY(false);
    @Getter
    private final boolean showInPublicList;
    public static List<RecipeState> getPublicStates() {
        return Arrays.stream(RecipeState.values()).filter(RecipeState::isShowInPublicList).toList();
    }
    RecipeState(boolean showInPublicList) {
        this.showInPublicList = showInPublicList;
    }
}
