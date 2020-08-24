package app.API.JavaFX.ShowHits;

import javafx.scene.layout.Region;

public final class TooltipHintRegionItem extends ATooltipHintItem<Region>{
    public TooltipHintRegionItem(Region attachedNode, ITooltipHintController tooltipHintController, String statusBarHint) {
        super(attachedNode, tooltipHintController, statusBarHint);
    }
}