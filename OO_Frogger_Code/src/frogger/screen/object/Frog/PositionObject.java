package frogger.screen.object.Frog;
import javafx.scene.Node;
import javafx.geometry.Bounds;
public abstract  class PositionObject{
    private double X;
    private double Y;
    private Node node ;
    public void setX(double x) {
        this.X = x;
    }
    public void setY(double y){
        this.Y=y;
    }
    public double getX() {
        return X;
    }
    public double getY() { return Y; }
    public double NodeY(){
        return node.getTranslateY();
    }
    public double NodeX(){
        return node.getTranslateX();
    }
    public Bounds bounds(){
        return node.getBoundsInParent();
    }
    public void move(double x,double y){
        setY(y);
        setX(x);
        this.node.setTranslateY(this.Y);
        this.node.setTranslateX(this.X);
    }
    public void setNode(Node x){
        this.node =x;
    }
    public boolean intersect(Node node){
        return node.getBoundsInParent().intersects(bounds());
    }
    public Node getNode(){
        return node;
    }

}
