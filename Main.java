import javax.swing.JOptionPane;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] palavras = {"bolo", "morango", "churrasco", "goiaba", "carne", "macarronada", "sorvete", "brigadeiro", "mousse", "kiwi", "batata"};
        String palavraAleatoria = obterPalavraAleatoria(palavras);
        char[] palavraSecreta = new char[palavraAleatoria.length()];
        boolean[] letrasReveladas = new boolean[palavraAleatoria.length()];
        int tentativasRestantes = 6;

        tornarPalavraSecreta(palavraSecreta);
        Menu();

        while (tentativasRestantes > 0) {
            char letra = obterLetraDoUsuario();
            boolean letraEncontrada = atualizarPalavraSecreta(palavraAleatoria, palavraSecreta, letra);

            if (!letraEncontrada) {
                tentativasRestantes--;
            }

            statusDoJogo(palavraSecreta, tentativasRestantes);

            if (palavraCompleta(palavraSecreta)) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você venceu! A palavra era: " + palavraAleatoria);
                System.exit(0);
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over! A palavra correta era: " + palavraAleatoria);
    }

    private static String obterPalavraAleatoria(String[] palavras) {
        Random random = new Random();
        int indice = random.nextInt(palavras.length);
        return palavras[indice];
    }

    private static void tornarPalavraSecreta(char[] palavraSecreta) {
        for (int i = 0; i < palavraSecreta.length; i++) {
            palavraSecreta[i] = '_';
        }
    }

    private static void Menu() {
        JOptionPane.showMessageDialog(null, "Bem-vindo ao Jogo da Forca!");
        JOptionPane.showMessageDialog(null, "Você deverá adivinhar a palavra adivinhando as letras que você acha que a compõe.");
        JOptionPane.showMessageDialog(null, "Você tem 6 tentativas.");
        JOptionPane.showMessageDialog(null, "Está preparado?");
        JOptionPane.showMessageDialog(null, "A dica é: COMIDA");
    }

    private static char obterLetraDoUsuario() {
        String letra = JOptionPane.showInputDialog("Digite uma letra:");
        return letra.charAt(0);
    }

    private static boolean atualizarPalavraSecreta(String palavraAleatoria, char[] palavraSecreta, char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavraAleatoria.length(); i++) {
            if (palavraAleatoria.charAt(i) == letra) {
                palavraSecreta[i] = letra;
                letraEncontrada = true;
            }
        }
        return letraEncontrada;
    }

    private static void statusDoJogo(char[] palavraSecreta, int tentativasRestantes) {
        String palavraAtual = new String(palavraSecreta);
        JOptionPane.showMessageDialog(null, "Palavra atual: " + palavraAtual + "\nTentativas restantes: " + tentativasRestantes);
    }

    private static boolean palavraCompleta(char[] palavraSecreta) {
        for (char c : palavraSecreta) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}