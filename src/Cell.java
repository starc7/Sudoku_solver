import javax.swing.JTextField;
import java.awt.Font;

//Cell class
class Cell extends JTextField{
    Cell() {
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("OCR A Extended", Font.PLAIN, 28));
    }
}
