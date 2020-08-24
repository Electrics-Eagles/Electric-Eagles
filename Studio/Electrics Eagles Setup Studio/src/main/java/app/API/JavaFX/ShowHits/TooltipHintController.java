package app.API.JavaFX.ShowHits;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Iterator;

public final class TooltipHintController implements ITooltipHintController {
    private final String DefaultStatusBarText = "";
    private final Labeled statusBarControl;
    private final ObservableList<ATooltipHintItem> tooltipHintItems;

    private boolean isStatusBarLocked = false;
    public boolean getIsStatusBarLocked() {
        return isStatusBarLocked;
    }
    public void setIsStatusBarLocked(boolean isStatusBarLocked) {
        this.isStatusBarLocked = isStatusBarLocked;
    }

    public Labeled getStatusBarControl() {
        return this.statusBarControl;
    }

    public void setStatusBarTextForce(String text) {
        if(statusBarControl == null) {
            return;
        }
        statusBarControl.setText(text);
    }
    @Override
    public void setStatusBarText(String text) {
        if(!isStatusBarLocked){
            setStatusBarTextForce(text);
        }
    }
    @Override
    public String getStatusBarText() {
        return (statusBarControl != null) ? statusBarControl.getText() : "";
    }
    @Override
    public void setDefaultStatusBarText(){
        setStatusBarTextForce(DefaultStatusBarText);
    }


    public void addTooltipHint(Region region, String statusBarHint){
        ATooltipHintItem tooltipHintItem = findTooltipHint(region);
        if(tooltipHintItem == null) {
            tooltipHintItem = new TooltipHintRegionItem(region, this, statusBarHint);
            tooltipHintItems.add(tooltipHintItem);
        } else {
            TooltipHintControlItem tooltipHintControlItem = (TooltipHintControlItem)tooltipHintItem;
            if(statusBarHint != null && tooltipHintControlItem.getStatusBarHint() == null)
                tooltipHintControlItem.setStatusBarHint(statusBarHint);
        }
    }

    public void addTooltipHint(Control control, String statusBarHint){
        addTooltipHint(control, statusBarHint, null, null);
    }

    public void addTooltipHint(Control control, String statusBarHint, String tooltipHint){
        addTooltipHint(control, statusBarHint, tooltipHint, null);
    }

    public void addTooltipHint(Control control, String statusBarHint, String tooltipHint, Image image){
        ATooltipHintItem tooltipHintItem = findTooltipHint(control);
        if(tooltipHintItem == null) {
            tooltipHintItem = new TooltipHintControlItem(control, this, statusBarHint, tooltipHint, image);
            tooltipHintItems.add(tooltipHintItem);
        } else {
            TooltipHintControlItem tooltipHintControlItem = (TooltipHintControlItem)tooltipHintItem;
            if(statusBarHint != null && tooltipHintControlItem.getStatusBarHint() == null)
                tooltipHintControlItem.setStatusBarHint(statusBarHint);

            if(tooltipHint != null && tooltipHintControlItem.getTooltipHint() == null)
                tooltipHintControlItem.setTooltipHint(tooltipHint);

            if(image != null && tooltipHintControlItem.getTooltipImage() == null)
                tooltipHintControlItem.setTooltipImage(image);
        }
    }

    public void removeTooltipHint(Node control){
        ATooltipHintItem tooltipHintItem = null;
        Iterator<ATooltipHintItem> iteratorTooltipHintItems = tooltipHintItems.iterator();
        while(iteratorTooltipHintItems.hasNext()){
            tooltipHintItem = iteratorTooltipHintItems.next();
            if(tooltipHintItem.getAttachedNode() == control){
                tooltipHintItems.remove(tooltipHintItem);
                break;
            }
        }
    }

    public ATooltipHintItem findTooltipHint(Node control){
        for(ATooltipHintItem tooltipHintItem : tooltipHintItems)
            if(tooltipHintItem.getAttachedNode() == control)
                return tooltipHintItem;
        return null;
    }

    public TooltipHintController(Labeled statusBarControl){
        this.statusBarControl = statusBarControl;
        tooltipHintItems = FXCollections.observableList(new ArrayList<>());
    }

    public TooltipHintController(){
        this(null);
    }

    private static TooltipHintController mainInstance;
    public static TooltipHintController getMainInstance() {
        if(mainInstance == null){
            mainInstance = new TooltipHintController();
        }

        return mainInstance;
    }
    public static void setMainInstance(TooltipHintController tooltipHintController) {
        mainInstance = tooltipHintController;
    }
}