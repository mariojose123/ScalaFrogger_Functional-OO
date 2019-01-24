package frogger.screen.`object`.Frog
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
 abstract class RetangleObject extends PositionObject {

  def setGameObjectRectangle( x: java.lang.Double, y: java.lang.Double,  Color:Paint) {
     if(x<=0 || y<=0) throw new ExceptionInInitializerError()
      var  GameObjectRectangle :  Rectangle = new Rectangle(x, y, Color);
      setNode(GameObjectRectangle);
      return;
  }

}