package INTERFACE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VIEW.Visao;

public class CatalogoForm
{
    private JPanel base;
    private JButton startButton;
    private JButton exitButton;

    public CatalogoForm() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Olá", "Inicialização", 1);
//                Visao.Visao();

//               Verificação de casas da sequencia
                int i = 0;
                String seq = "";
                while(seq.equals(""))
                    seq = JOptionPane.showInputDialog(null,"Número de casas da sequência","Digite um inteiro",1);
                try {i = Integer.parseInt(seq);}
                catch (NumberFormatException err) {System.out.println("Numero com formato errado!");}
                Visao.ExibeConsulta(i);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("App");
        window.setContentPane(new CatalogoForm().base);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(600, 500));
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
}
