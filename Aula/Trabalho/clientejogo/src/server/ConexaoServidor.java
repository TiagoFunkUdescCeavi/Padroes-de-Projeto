package server;

import controller.ObservadorServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConexaoServidor implements Runnable{
    
    private String host;
    private int porta;
    
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    
    public ConexaoServidor( String host, int porta ){
        this.host = host;
        this.porta = porta;
    }
    
    private List<ObservadorServidor> listaObservadores = new ArrayList<>();
    
    public void addObservador( ObservadorServidor obs ){
        listaObservadores.add( obs );
    }
    
    public void removeObservador( ObservadorServidor obs ){
        listaObservadores.remove( obs );
    }
    
    public void abrirConexao() throws IOException{
        socket = new Socket( host, porta );
        output = new ObjectOutputStream( socket.getOutputStream() );
        input = new ObjectInputStream( socket.getInputStream() );
    }
    
    public void enviar( String mensagem ) throws IOException{
        output.writeUTF( mensagem );
        output.flush();
        System.out.println("Enviado: " + mensagem );
    }
      
    public void fecharConexao() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
    
    public boolean estaConectado(){
        return socket.isConnected();
    }

    @Override
    public void run() {
        String mensagem = "";
        while( true ){
            try {
                mensagem = input.readUTF();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep( 100 );
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if( mensagem != null && !mensagem.isEmpty() ){
                System.out.println(porta + " mensagem: " + mensagem);
                for( ObservadorServidor obs : listaObservadores ){
                    obs.encaminharMensagemRecebida( mensagem );
                }
            }
        }
    }
}
