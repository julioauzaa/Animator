import static cs5004.animator.utils.AnimationReader.parseFile;
import static org.junit.Assert.assertEquals;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

/**
 * JUnit test class for the IAnimatorView interface.
 */
public class IAnimatorViewTest {

  // private method that populates the model manually following the cycle that the builder follows
  private IAnimatorModel populateModelManual() {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    // First set canvas
    builder.setBounds(200, 70, 360, 360);

    // Second declare shapes with no attributes
    builder.declareShape("R", "rectangle");
    builder.declareShape("C", "ellipse");

    // Motion to the shapes
    // The first motion of the shape sets attributes to the shape (equal value on both columns)
    builder.addMotion("R", 1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);

    builder.addMotion("R", 10, 200, 200, 50, 100, 255, 0, 0,
        50, 300, 300, 50, 100, 255, 0, 0);

    builder.addMotion("R", 50, 300, 300, 50, 100, 255, 0, 0,
        51, 300, 300, 50, 100, 255, 0, 0);

    builder.addMotion("R", 51, 300, 300, 50, 100, 255, 0, 0,
        70, 300, 300, 25, 100, 255, 0, 0);

    builder.addMotion("R", 70, 300, 300, 25, 100, 255, 0, 0,
        100, 200, 200, 25, 100, 255, 0, 0);


    // "C" is next: first line equal values to set attributes
    builder.addMotion("C", 6, 440, 70, 120, 60, 0, 0, 255,
        20, 440, 70, 120, 60, 0, 0, 255);

    builder.addMotion("C", 20, 440, 70, 120, 60, 0, 0, 255,
        50, 440, 250, 120, 60, 0, 0, 255);

    builder.addMotion("C", 50, 440, 250, 120, 60, 0, 0, 255,
        70, 440, 370, 120, 60, 0, 170, 85);

    builder.addMotion("C", 70, 440, 370, 120, 60, 0, 170, 85,
        80, 440, 370, 120, 60, 0, 255, 0);

    builder.addMotion("C", 80, 440, 370, 120, 60, 0, 255, 0,
        100, 440, 370, 120, 60, 0, 255, 0);

    return builder.build();
  }

  // private method that populates the model automatically using the method parseFile
  private IAnimatorModel populateModelAutomatic() throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    Readable in = new FileReader("test/smalldemo.txt");

    return parseFile(in, builder);
  }

  @Test
  public void testSVGView() throws FileNotFoundException {
    // testing TextView manually populated model
    IAnimatorModel manualModel = populateModelManual();
    SVGView svgManual = new SVGView();
    String[] args = {"-in", "in", "-view", "playback"};
    IAnimatorController control = new AnimatorControllerImpl(args, manualModel, svgManual);
    assertEquals("<svg viewBox=\"200 70 360 360\" version=\"1.1\" "
                 + "xmlns=\"http://www.w3.org/2000/svg\">\n"
                 + "\n"
                 + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
                 + "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
                 + "\t<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" "
                 + "begin=\"100ms\" dur=\"900ms\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
                 + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
                 + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"100ms\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" "
                 + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" "
                 + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" "
                 + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" "
                 + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                 + "</rect>\n"
                 + "\n"
                 + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" "
                 + "fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
                 + "\t<set attributeName=\"visibility\" attributeType=\"CSS\" "
                 + "to=\"visible\" begin=\"600ms\" dur=\"1400ms\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
                 + "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
                 + "attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\" "
                 + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" "
                 + "fill=\"freeze\" />\n"
                 + "</ellipse>\n\n</svg>", control.getSVGView());


    // testing automated populated model
    IAnimatorModel automaticModel = populateModelAutomatic();
    SVGView svgAuto = new SVGView();
    IAnimatorController control2 = new AnimatorControllerImpl(args, automaticModel, svgAuto);
    assertEquals("<svg viewBox=\"200 70 360 360\" version=\"1.1\" "
                 + "xmlns=\"http://www.w3.org/2000/svg\">\n"
                 + "\n"
                 + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
                 + "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
                 + "\t<set attributeName=\"visibility\" attributeType=\"CSS\" "
                 + "to=\"visible\" begin=\"100ms\" dur=\"900ms\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
                 + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
                 + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"100ms\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" "
                 + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" "
                 + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" "
                 + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" "
                 + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                 + "</rect>\n\n"
                 + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" "
                 + "fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
                 + "\t<set attributeName=\"visibility\" attributeType=\"CSS\" "
                 + "to=\"visible\" begin=\"600ms\" dur=\"1400ms\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
                 + "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
                 + "attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" "
                 + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\" "
                 + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" "
                 + "fill=\"freeze\" />\n</ellipse>\n\n"
                 + "</svg>", control2.getSVGView());
  }

  @Test
  public void testTextView() throws FileNotFoundException {
    // testing TextView manually populated model
    IAnimatorModel manualModel = populateModelManual();
    TextView textManual = new TextView();
    String[] args = {"-in", "in", "-view", "playback"};
    IAnimatorController control3 = new AnimatorControllerImpl(args, manualModel, textManual);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
                 + "Appears at t=1\n"
                 + "Disappears at t=100\n"
                 + "\n"
                 + "Name: C\n"
                 + "Type: ellipse\n"
                 + "Center: (440.0,70.0), X radius: 120.0, Y radius: 60.0, Color: (0.0,0.0,255.0)\n"
                 + "Appears at t=6\n"
                 + "Disappears at t=100\n"
                 + "\n"
                 + "Speed of animation: 1 tick(s) per second\n"
                 + "\n"
                 + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from time t=10 to t=50\n"
                 + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                 + "Height: 100.0 from time t=51 to t=70\n"
                 + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from time t=70 to t=100\n"
                 + "Shape C moves from (440.0, 70.0) to (440.0, 250.0) from time t=20 to t=50\n"
                 + "Shape C moves from (440.0, 250.0) to (440.0, 370.0) from time t=50 to t=70\n"
                 + "Shape C changes color from (0.0,0.0,255.0) to (0.0,170.0,85.0) "
                 + "from time t=50 to t=70\n"
                 + "Shape C changes color from (0.0,170.0,85.0) to (0.0,255.0,0.0) "
                 + "from time t=70 to t=80\n", control3.getTextView());

    // testing TextView automated populated model
    IAnimatorModel automaticModel = populateModelAutomatic();
    TextView textAuto = new TextView();
    IAnimatorController control4 = new AnimatorControllerImpl(args, automaticModel, textAuto);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
                 + "Appears at t=1\n"
                 + "Disappears at t=100\n"
                 + "\n"
                 + "Name: C\n"
                 + "Type: ellipse\n"
                 + "Center: (440.0,70.0), X radius: 120.0, Y radius: 60.0, Color: (0.0,0.0,255.0)\n"
                 + "Appears at t=6\n"
                 + "Disappears at t=100\n"
                 + "\n"
                 + "Speed of animation: 1 tick(s) per second\n"
                 + "\n"
                 + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from time t=10 to t=50\n"
                 + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
                 + "from time t=51 to t=70\n"
                 + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from time t=70 to t=100\n"
                 + "Shape C moves from (440.0, 70.0) to (440.0, 250.0) from time t=20 to t=50\n"
                 + "Shape C moves from (440.0, 250.0) to (440.0, 370.0) from time t=50 to t=70\n"
                 + "Shape C changes color from (0.0,0.0,255.0) to (0.0,170.0,85.0) "
                 + "from time t=50 to t=70\n"
                 + "Shape C changes color from (0.0,170.0,85.0) to (0.0,255.0,0.0) "
                 + "from time t=70 to t=80\n", control4.getTextView());
  }
}
