import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class gui {
    private JFrame frame;
    private JTextField summonerNameField;
    private JTextArea resultArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                gui window = new gui();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public gui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblSummonerName = new JLabel("召唤师名称:");
        lblSummonerName.setBounds(10, 10, 100, 25);
        frame.getContentPane().add(lblSummonerName);

        summonerNameField = new JTextField();
        summonerNameField.setBounds(120, 10, 200, 25);
        frame.getContentPane().add(summonerNameField);
        summonerNameField.setColumns(10);

        JButton btnSearch = new JButton("查询");
        btnSearch.setBounds(330, 10, 80, 25);
        frame.getContentPane().add(btnSearch);

        JButton btnCheckClient = new JButton("检查客户端是否运行");
        btnCheckClient.setBounds(120, 50, 200, 25);
        frame.getContentPane().add(btnCheckClient);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 90, 400, 150);
        frame.getContentPane().add(resultArea);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String summonerName = summonerNameField.getText();
                try {
                    String result = searchSummoner(summonerName);
                    resultArea.setText(result);
                } catch (IOException ex) {
                    resultArea.setText("查询失败: " + ex.getMessage());
                }
            }
        });

        btnCheckClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isRunning = checkLolClientRunning();
                if (isRunning) {
                    JOptionPane.showMessageDialog(frame, "英雄联盟客户端正在运行。");
                } else {
                    JOptionPane.showMessageDialog(frame, "英雄联盟客户端未运行。");
                }
            }
        });
    }

    private String searchSummoner(String summonerName) throws IOException {
        // 调用Python脚本或API来查询召唤师战绩
        // 这里假设有一个Python脚本可以通过命令行调用
        ProcessBuilder pb = new ProcessBuilder("python", "untitled/main.py", summonerName);
        Process process = pb.start();
        List<String> results = new BufferedReader(new InputStreamReader(process.getInputStream())).lines().collect(Collectors.toList());
        return String.join("\n", results);
    }

    private boolean checkLolClientRunning() {
        // 检查进程列表中是否有英雄联盟客户端
        for (ProcessHandle processHandle : ProcessHandle.allProcesses()) {
            if (processHandle.info().command().isPresent() && processHandle.info().command().get().contains("LeagueClient.exe")) {
                return true;
            }
        }
        return false;
    }
}
