package safeguard.ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class PainelCadastroVitima extends JPanel {

    private JTextField fNome, fCpf, fTelefone, fEndereco, fMedida;
    private JComboBox<String> cbStatus;
    private JTextArea taObs;

    public PainelCadastroVitima() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);

        JScrollPane scroll = new JScrollPane(criarConteudo());
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Cores.FUNDO);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel criarConteudo() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Cores.FUNDO);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de vítima");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titulo.setForeground(Cores.TEXTO_PRINCIPAL);
        p.add(titulo);
        p.add(Box.createVerticalStrut(14));

        JTabbedPane abas = new JTabbedPane();
        abas.setFont(Cores.FONTE_CAMPO);
        abas.addTab("Novo cadastro", criarAbaFormulario());
        abas.addTab("Vítimas cadastradas", criarAbaLista());
        abas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
        p.add(abas);

        return p;
    }

    private JPanel criarAbaFormulario() {
        JPanel aba = new JPanel();
        aba.setLayout(new BoxLayout(aba, BoxLayout.Y_AXIS));
        aba.setBackground(Cores.FUNDO);
        aba.setBorder(new EmptyBorder(14, 0, 0, 0));

        JPanel card1 = Componentes.card();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
        card1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel sec1 = new JLabel("DADOS PESSOAIS");
        sec1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sec1.setForeground(Cores.TEXTO_SECUNDARIO);
        card1.add(sec1);
        card1.add(Box.createVerticalStrut(10));

        JPanel row1 = new JPanel(new GridLayout(1, 2, 12, 0));
        row1.setBackground(Color.WHITE);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        JPanel g1 = grupo("Nome completo *"); fNome = Componentes.campo("Nome da vítima"); g1.add(fNome); row1.add(g1);
        JPanel g2 = grupo("CPF"); fCpf = Componentes.campo("000.000.000-00"); g2.add(fCpf); row1.add(g2);
        card1.add(row1);
        card1.add(Box.createVerticalStrut(10));

        JPanel row2 = new JPanel(new GridLayout(1, 2, 12, 0));
        row2.setBackground(Color.WHITE);
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        JPanel g3 = grupo("Telefone *"); fTelefone = Componentes.campo("(27) 99999-9999"); g3.add(fTelefone); row2.add(g3);
        JPanel g4 = grupo("Endereço *"); fEndereco = Componentes.campo("Rua, número, bairro"); g4.add(fEndereco); row2.add(g4);
        card1.add(row2);

        JPanel card2 = Componentes.card();
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        card2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel sec2 = new JLabel("MEDIDA PROTETIVA");
        sec2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sec2.setForeground(Cores.TEXTO_SECUNDARIO);
        card2.add(sec2);
        card2.add(Box.createVerticalStrut(10));

        JPanel row3 = new JPanel(new GridLayout(1, 2, 12, 0));
        row3.setBackground(Color.WHITE);
        row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        JPanel g5 = grupo("Número da medida protetiva"); fMedida = Componentes.campo("N.º do processo"); g5.add(fMedida); row3.add(g5);
        JPanel g6 = grupo("Status");
        cbStatus = Componentes.combo(new String[]{"Ativa", "Suspensa", "Encerrada"});
        g6.add(cbStatus); row3.add(g6);
        card2.add(row3);
        card2.add(Box.createVerticalStrut(10));

        JPanel g7 = grupo("Observações");
        taObs = Componentes.areaTexto(3);
        g7.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        g7.add(Componentes.scroll(taObs));
        card2.add(g7);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(Cores.FUNDO);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        JButton salvar = Componentes.botaoPrimario("✓ Salvar cadastro");
        JButton limpar = Componentes.botaoSecundario("Limpar");
        salvar.addActionListener(e -> salvarVitima());
        limpar.addActionListener(e -> limparFormulario());
        btnRow.add(salvar); btnRow.add(limpar);

        aba.add(card1); aba.add(Box.createVerticalStrut(12));
        aba.add(card2); aba.add(Box.createVerticalStrut(12));
        aba.add(btnRow);
        return aba;
    }

    private JPanel criarAbaLista() {
        JPanel aba = new JPanel(new BorderLayout(0, 10));
        aba.setBackground(Cores.FUNDO);
        aba.setBorder(new EmptyBorder(14, 0, 0, 0));

        JPanel busca = new JPanel(new BorderLayout(8, 0));
        busca.setBackground(Cores.FUNDO);
        JTextField fBusca = Componentes.campo("Buscar por nome ou CPF...");
        JButton btnBuscar = Componentes.botaoSecundario("Buscar");
        busca.add(fBusca, BorderLayout.CENTER);
        busca.add(btnBuscar, BorderLayout.EAST);

        String[] colunas = {"ID", "Nome", "Telefone", "Medida Protetiva", "Status"};
        Object[][] dados = {
            {"#001", "Ana Paula S.", "(27) 99100-2233", "0012345-67", "Ativa"},
            {"#002", "Carla M.",     "(27) 98877-5544", "0098765-32", "Ativa"},
            {"#003", "Fernanda R.",  "(27) 99911-0022", "—",          "Suspensa"},
        };
        JTable tabela = new JTable(dados, colunas);
        tabela.setFont(Cores.FONTE_CAMPO);
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.setGridColor(Cores.BORDA);
        tabela.setSelectionBackground(Cores.AZUL_CLARO);
        tabela.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(tabela);
        sp.setBorder(new LineBorder(Cores.BORDA));

        aba.add(busca, BorderLayout.NORTH);
        aba.add(sp, BorderLayout.CENTER);
        return aba;
    }

    private JPanel grupo(String rotulo) {
        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.Y_AXIS));
        g.setBackground(Color.WHITE);
        JLabel l = Componentes.label(rotulo);
        l.setBorder(new EmptyBorder(0, 0, 4, 0));
        g.add(l);
        return g;
    }

    private void salvarVitima() {
        if (fNome.getText().isEmpty() || fNome.getText().equals("Nome da vítima")) {
            JOptionPane.showMessageDialog(this, "Informe o nome da vítima.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Vítima cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparFormulario();
    }

    private void limparFormulario() {
        fNome.setText(""); fCpf.setText(""); fTelefone.setText("");
        fEndereco.setText(""); fMedida.setText(""); taObs.setText("");
        cbStatus.setSelectedIndex(0);
    }
}
