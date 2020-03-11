package API.JavaFX.ShowHits;

import javafx.scene.Node;

public abstract class ATooltipHintItem<N extends Node> {
    private N attachedNode;
    protected void setAttachedNode(N node) {
        attachedNode = node;
    }
    public N getAttachedNode() {
        return attachedNode;
    }

    private String statusBarHint;
    protected void setStatusBarHint(String hint){
        statusBarHint = hint;
    }
    public String getStatusBarHint(){
        return statusBarHint;
    }

    private ITooltipHintController tooltipHintController;
    public ITooltipHintController getTooltipHintController() {
        return tooltipHintController;
    }

    public void showStatusBarHint(){
        tooltipHintController.setStatusBarText(statusBarHint);
    }

    public ATooltipHintItem(N attachedNode, ITooltipHintController tooltipHintController, String statusBarHint) {
        this.attachedNode = attachedNode;
        this.tooltipHintController = tooltipHintController;

        if(statusBarHint != null && !statusBarHint.equals("")){
            initStatusBar();
            this.setStatusBarHint(statusBarHint);
        }
    }

    private void initStatusBar() {
        getAttachedNode().setOnMouseEntered(observableValue -> {
            this.showStatusBarHint();
        });

        getAttachedNode().setOnMouseExited(observableValue -> {
            getTooltipHintController().setDefaultStatusBarText();
        });
    }
}