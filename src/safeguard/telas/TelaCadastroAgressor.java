package safeguard.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaCadastroAgressor extends JFrame {

    private JTextField campoNome, campoCPF, campoTelefone, campoEndereco;
    private JComboBox<String> comboVitima, comboVinculo;
    private JTextArea campoDesc;

    public TelaCadastroAgressor() {
        setTitle("SafeGuard — Cadastro de Agressor");
        setSize(720, 540);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(TelaPrincipal.COR_CONTEUDO);

        add(criarTopBar(), BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(TelaPrincipal.COR_CONTEUDO);
        conteudo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de agressor");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 60));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(titulo);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel cardDados = TelaPrincipal.criarCard("Dados do agressor");
        JPanel grid = new JPanel(new GridLayout(3, 2, 12, 10));
        grid.setBackground(Color.WHITE);
        grid.add(criarGrupo("Nome completo *", campoNome     = criarCampo("Nome do agressor")));
        grid.add(criarGrupo("CPF *",           campoCPF      = criarCampo("000.000.000-00")));
        grid.add(criarGrupo("Telefone",        campoTelefone = criarCampo("(27) 99999-9999")));
        grid.add(criarGrupo("Endereço",        campoEndereco = criarCampo("Último endereço conhecido")));
        grid.add(new JPanel());
        grid.add(new JPanel());
        cardDados.add(grid, BorderLayout.CENTER);
        conteudo.add(cardDados);
        conteudo.add(Box.createVerticalStrut(12));

        JPanel cardVinculo = TelaPrincipal.criarCard("Vínculo com vítima");
        JPanel gridV = new JPanel(new GridLayout(2, 2, 12, 10));
        gridV.setBackground(Color.WHITE);
        comboVitima = new JComboBox<>(new String[]{"— selecione —", "Ana Paula S.", "Carla M.", "Fernanda R."});
        comboVitima.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboVinculo = new JComboBox<>(new String[]{"Ex-cônjuge", "Companheiro(a)", "Familiar", "Outro"});
        comboVinculo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gridV.add(criarGrupoComp("Vítima associada *", comboVitima));
        gridV.add(criarGrupoComp("Tipo de vínculo", comboVinculo));
        campoDesc = new JTextArea(2, 20);
        campoDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campoDesc.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        campoDesc.setLineWrap(true);
        JPanel descPanel = new JPanel(new BorderLayout(0, 4));
        descPanel.setBackground(Color.WHITE);
        JLabel descLabel = new JLabel("Descrição / histórico");
        descLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        descLabel.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        descPanel.add(descLabel, BorderLayout.NORTH);
        descPanel.add(new JScrollPane(campoDesc), BorderLayout.CENTER);
        gridV.add(descPanel);
        gridV.add(new JPanel());
        cardVinculo.add(gridV, BorderLayout.CENTER);
        conteudo.add(cardVinculo);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(TelaPrincipal.COR_CONTEUDO);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRow.add(TelaPrincipal.criarBotaoPrimario("✔ Salvar cadastro", this::salvar));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Limpar", this::limpar));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Fechar", this::dispose));
        conteudo.add(btnRow);

        add(new JScrollPane(conteudo), BorderLayout.CENTER);
    }

    private JPanel criarTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(TelaPrincipal.COR_PRIMARIA);
        bar.setPreferredSize(new Dimension(0, 46));
        bar.setBorder(new EmptyBorder(0, 16, 0, 16));
        JLabel logo = new JLabel("🛡  SafeGuard");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        logo.setForeground(Color.WHITE);
        bar.add(logo, BorderLayout.WEST);
        JLabel tit = new JLabel("Cadastro de agressor");
        tit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tit.setForeground(new Color(200, 220, 240));
        bar.add(tit, BorderLayout.EAST);
        return bar;
    }

    private JTextField criarCampo(String placeholder) {
        JTextField f = new JTextField(placeholder);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(4, 8, 4, 8)));
        return f;
    }

    private JPanel criarGrupo(String label, JTextField campo) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        p.add(lbl, BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
    }

    private JPanel criarGrupoComp(String label, JComponent comp) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        p.add(lbl, BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private void salvar() {
        if (campoNome.getText().trim().isEmpty() || campoCPF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios (*).", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Agressor cadastrado com sucesso!", "SafeGuard", JOptionPane.INFORMATION_MESSAGE);
        limpar();
    }

    private void limpar() {
        campoNome.setText("");
        campoCPF.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoDesc.setText("");
        comboVitima.setSelectedIndex(0);
        comboVinculo.setSelectedIndex(0);
    }

}   