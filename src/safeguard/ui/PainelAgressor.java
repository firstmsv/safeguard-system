package safeguard.ui;

import javax.swing.*;
import java.awt.*;

public class PainelAgressor extends JPanel {

    private JTextField campNome, campCpf, campTel, campEnd;
    private JComboBox<String> comboVitima, comboVinculo;
    private JTextArea campDesc;
    private JLabel lblMsg;

    public PainelAgressor() {
        setLayout(new BorderLayout());
        setBackground(Estilo.CINZA_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de Agressor");
        titulo.setFont(Estilo.FONTE_TITULO);
        titulo.setForeground(Estilo.TEXTO_PRIMARIO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Estilo.CINZA_BG);

        JPanel card = Estilo.painelCard();
        card.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        campNome  = Estilo.campo("");
        campCpf   = Estilo.campo("");
        campTel   = Estilo.campo("");
        campEnd   = Estilo.campo("");
        comboVitima  = Estilo.combo(new String[]{"-- selecione --", "Ana Paula S.", "Carla M.", "Fernanda R."});
        comboVinculo = Estilo.combo(new String[]{"Ex-conjuge", "Companheiro(a)", "Familiar", "Outro"});
        campDesc = new JTextArea(2, 20);
        campDesc.setFont(Estilo.FONTE_INPUT);
        campDesc.setBorder(BorderFactory.createLineBorder(Estilo.CINZA_BORDA));

        g.gridx=0; g.gridy=0; g.weightx=0; card.add(Estilo.label("Nome completo *"), g);
        g.gridx=1; g.weightx=1; card.add(campNome, g);
        g.gridx=2; g.weightx=0; card.add(Estilo.label("CPF *"), g);
        g.gridx=3; g.weightx=1; card.add(campCpf, g);

        g.gridy=1; g.gridx=0; g.weightx=0; card.add(Estilo.label("Telefone"), g);
        g.gridx=1; g.weightx=1; card.add(campTel, g);
        g.gridx=2; g.weightx=0; card.add(Estilo.label("Vitima vinculada *"), g);
        g.gridx=3; g.weightx=1; card.add(comboVitima, g);

        g.gridy=2; g.gridx=0; g.weightx=0; card.add(Estilo.label("Tipo de vinculo"), g);
        g.gridx=1; g.weightx=1; card.add(comboVinculo, g);
        g.gridx=2; g.weightx=0; card.add(Estilo.label("Endereco"), g);
        g.gridx=3; g.weightx=1; card.add(campEnd, g);

        g.gridy=3; g.gridx=0; g.gridwidth=1; g.weightx=0; card.add(Estilo.label("Descricao / historico"), g);
        g.gridx=1; g.gridwidth=3; g.weightx=1; card.add(new JScrollPane(campDesc), g);

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));
        centro.add(card);
        centro.add(Box.createVerticalStrut(10));

        lblMsg = new JLabel(" ");
        lblMsg.setFont(Estilo.FONTE_SMALL);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(Estilo.CINZA_BG);
        JButton btnSalvar = Estilo.botaoPrimario("Salvar Cadastro");
        JButton btnLimpar = Estilo.botaoSecundario("Limpar");
        btnSalvar.addActionListener(e -> salvar());
        btnLimpar.addActionListener(e -> limpar());
        btnRow.add(btnSalvar);
        btnRow.add(btnLimpar);
        btnRow.add(lblMsg);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centro.add(btnRow);

        add(centro, BorderLayout.CENTER);
    }

    private void salvar() {
        if (campNome.getText().trim().isEmpty() || campCpf.getText().trim().isEmpty()) {
            lblMsg.setForeground(Estilo.VERMELHO);
            lblMsg.setText("Preencha os campos obrigatorios (*).");
            return;
        }
        lblMsg.setForeground(Estilo.AZUL_PRIMARIO);
        lblMsg.setText("Agressor cadastrado com sucesso!");
        JOptionPane.showMessageDialog(this, "Agressor \"" + campNome.getText() + "\" cadastrado!", "SafeGuard", JOptionPane.INFORMATION_MESSAGE);
        limpar();
    }

    private void limpar() {
        campNome.setText(""); campCpf.setText(""); campTel.setText(""); campEnd.setText("");
        campDesc.setText(""); comboVitima.setSelectedIndex(0); comboVinculo.setSelectedIndex(0);
        lblMsg.setText(" ");
    }
}
