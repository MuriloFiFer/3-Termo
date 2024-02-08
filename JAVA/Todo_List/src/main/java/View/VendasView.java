// package main.java.View;

// import java.awt.List;

// import javax.swing.JComboBox;
// import javax.swing.JPanel;

// import main.java.Connection.CarrosDAO;
// import main.java.Model.Carros;

// public class VendasView extends JPanel {
//     JComboBox<String> carrosComboBox;
//     List<Carros> carros;


//     public VendasView() {
//         super();
//         carrosComboBox = new JComboBox<>();

//         // Preencha o JComboBox com os carros
//         carros + new CarrosDAO().listarTodos();
//         carrosComboBox.addItem("Selecione o Carro");
//         for (Carros carro : carros) {
//             carrosComboBox.addItem(carro.getMarca()
//             + " " + carro.getModelo()
//             + " " + carro.getPlaca());
//         }
//         add(carrosComboBox);
//     }
// }
