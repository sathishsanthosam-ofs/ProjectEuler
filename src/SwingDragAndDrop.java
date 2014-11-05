import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class SwingDragAndDrop
{
    JTextField txtField;

    JLabel lbl;

    public static void main(final String[] args)
    {
        final SwingDragAndDrop sdd = new SwingDragAndDrop();
    }

    public SwingDragAndDrop()
    {
        final JFrame frame = new JFrame("Drag Drop Demo");
        txtField = new JTextField(20);
        lbl = new JLabel("This is the text for drag and drop.");
        lbl.setTransferHandler(new TransferHandler("text"));
        final MouseListener ml = new MouseAdapter()
        {
            public void mousePressed(final MouseEvent e)
            {
                final JComponent jc = (JComponent) e.getSource();
                final TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
                System.out.println("Is Being Tragged");
            }

            @Override
            public void mouseReleased(final MouseEvent e)
            {
                System.out.println("mouseReleased");
            }

            @Override
            public void mouseExited(final MouseEvent e)
            {
                System.out.println("mouseExisted");
            }

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e)
            {
                System.out.println("mouseWheelMoved");
            }

            @Override
            public void mouseDragged(final MouseEvent e)
            {
                System.out.println("mouseDragged");
            }

            @Override
            public void mouseMoved(final MouseEvent e)
            {
                System.out.println("mouseMoved");
            }
        };


        lbl.addMouseListener(ml);
        // txtField.addMouseListener(m2);
        final JPanel panel = new JPanel();
        panel.add(txtField);
        frame.add(lbl, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}