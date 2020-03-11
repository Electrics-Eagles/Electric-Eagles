package API.JavaFX.ShowHits;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class TooltipHintControlItem extends ATooltipHintItem<Control> {
    private Tooltip tooltip;
    public Tooltip getTooltip() {
        return tooltip;
    }

    private String tooltipHint;
    public TooltipHintControlItem setTooltipHint(String hint){
        tooltipHint = hint;
        if(hint == null || hint.trim().length() <= 0)
            return this;
        if(tooltip == null) {
            initTooltip();
        }
        tooltip.setText(hint);
        return this;
    }
    public String getTooltipHint(){
        return tooltipHint;
    }

    private Image tooltipImage;
    public TooltipHintControlItem setTooltipImage(Image image){
        tooltipImage = image;
        if(tooltip != null) tooltip.setGraphic((image != null) ? new ImageView(image) : null);
        return this;
    }
    public Image getTooltipImage(){
        return tooltipImage;
    }

    public TooltipHintControlItem(Control attachedNode, ITooltipHintController tooltipHintController, String statusBarHint, String tooltipHint, Image imageHint) {
        super(attachedNode, tooltipHintController, statusBarHint);
        if(tooltipHint != null && !tooltipHint.isEmpty()){
            initTooltip();
        }
        setTooltipHint(tooltipHint);

        if(imageHint == null) {
            setTooltipImage(imageHint);
        }
    }

    public TooltipHintControlItem(Control attachedNode, ITooltipHintController tooltipHintController, String statusBarHint, String tooltipHint) {
        this(attachedNode, tooltipHintController, statusBarHint, tooltipHint, null);
    }

    public TooltipHintControlItem(Control attachedNode, ITooltipHintController tooltipHintController, String statusBarHint) {
        this(attachedNode, tooltipHintController, statusBarHint, null, null);
    }

    private void initTooltip() {
        tooltip = new Tooltip();
        getAttachedNode().setTooltip(tooltip);
    }
}