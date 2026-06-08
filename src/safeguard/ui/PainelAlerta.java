package safeguard.ui;

import javax.swing.*;
import java.awt.*;

public class PainelAlerta extends JPanel {

    private JComboBox<String> comboOcorr, comboTipo;
    private JTextArea campMsg;
    private JLabel lblMsg;

    public PainelAlerta() {
        setLayout(new BorderLayout());
        setBackground(Estilo.CINZA_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Emissao de Alertas");
        titulo.setFont(Estilo.FONTE_TITULO);
        titulo.setForeground(Estilo.TEXTO_PRIMARIO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Estilo.CINZA_BG);

        JPanel cardForm = Estilo.painelCard();
        cardForm.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        comboOcorr = Estilo.combo(new String[]{"-- selecione --", "#005 - Ana Paula S. (04/06)", "#004 - Carla M. (02/06)"});
        comboTipo  = Estilo.combo(new String[]{"Emergencia", "Aviso", "Monitoramento"});
        campMsg = new JTextArea(3, 20);
        campMsg.setFont(Estilo.FONTE_INPUT);
        campMsg.setBorder(BorderFactory.createLineBorder(Estilo.CINZA_BORDA));

        g.gridx=0; g.gridy=0; g.weightx=0; cardForm.add(Estilo.label("Ocorrencia vinculada *"), g);
        g.gridx=1; g.weightx=1; cardForm.add(comboOcorr, g);
        g.gridx=2; g.weightx=0; cardForm.add(Estilo.label("Tipo de alerta *"), g);
        g.gridx=3; g.weightx=1; cardForm.add(comboTipo, g);

        g.gridy=1; g.gridx=0; g.gridwidth=1; g.weightx=0; cardForm.add(Estilo.label("Mensagem *"), g);
        g.gridx=1; g.gridwidth=3; g.weightx=1; cardForm.add(new JScrollPane(campMsg), g);

        cardForm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        centro.add(cardForm);
        centro.add(Box.createVerticalStrut(10));

        lblMsg = new JLabel(" ");
        lblMsg.setFont(Estilo.FONTE_SMALL);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(Estilo.CINZA_BG);
        JButton btnEmitir   = Estilo.botaoPerigo("Emitir Alerta");
        JButton btnCancelar = Estilo.botaoSecundario("Cancelar");
        btnEmitir.addActionListener(e -> emitir());
        btnRow.add(btnEmitir);
        btnRow.add(btnCancelar);
        btnRow.add(lblMsg);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centro.add(btnRow);
        centro.add(Box.createVerticalStrut(14));

        JPanel cardLista = Estilo.painelCard();
        cardLista.setLayout(new BorderLayout());
        JLabel lblLista = new JLabel("Alertas emitidos");
        lblLista.setFont(Estilo.FONTE_LABEL);
        lblLista.setForeground(Estilo.TEXTO_MUTED);
        lblLista.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        cardLista.add(lblLista, BorderLayout.NORTH);

        JPanel lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.setBackground(Estilo.BRANCO);
        lista.add(itemAlerta("Emergencia","#005 - Ana Paula S.","04/06 14:32","Agressor avistado proximo.",Estilo.VERMELHO_BG,Estilo.VERMELHO));
        lista.add(Box.createVerticalStrut(6));
        lista.add(itemAlerta("Aviso","#004 - Carla M.","02/06 09:15","Descumprimento de medida.",Estilo.AMBER_BG,Estilo.AMBER));
        lista.add(Box.createVerticalStrut(6));
        lista.add(itemAlerta("Monitoramento","#003 - Fernanda R.","28/05 11:00","Acompanhamento encerrado.",Estilo.AZUL_CLARO,Estilo.AZUL_PRIMARIO));
        cardLista.add(lista, BorderLayout.CENTER);
        cardLista.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        centro.add(cardLista);

        add(centro, BorderLayout.CENTER);
    }

    private JPanel itemAlerta(String tipo,String ref,String data,String msg,Color bg,Color cor){
        JPanel p=new JPanel(new BorderLayout());
        p.setBackground(bg);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0,3,0,0,cor),
            BorderFactory.createEmptyBorder(8,10,8,10)));
        JLabel t=new JLabel("["+tipo+"]  "+ref);
        t.setFont(Estilo.FONTE_LABEL); t.setForeground(cor);
        JLabel d=new JLabel(data+" — "+msg);
        d.setFont(Estilo.FONTE_SMALL); d.setForeground(Estilo.TEXTO_MUTED);
        p.add(t,BorderLayout.NORTH); p.add(d,BorderLayout.SOUTH);
        return p;
    }

    private void emitir(){
        if(comboOcorr.getSelectedIndex()==0||campMsg.getText().trim().isEmpty()){
            lblMsg.setForeground(Estilo.VERMELHO);
            lblMsg.setText("Selecione a ocorrencia e informe a mensagem.");
            return;
        }
        String tipo=(String)comboTipo.getSelectedItem();
        lblMsg.setForeground(Estilo.AZUL_PRIMARIO);
        lblMsg.setText("Alerta ["+tipo+"] emitido!");
        JOptionPane.showMessageDialog(this,"Alerta de "+tipo+" emitido!","SafeGuard",JOptionPane.WARNING_MESSAGE);
        campMsg.setText(""); comboOcorr.setSelectedIndex(0);
    }
}
