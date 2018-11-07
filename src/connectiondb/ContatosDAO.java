/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectiondb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contato;

/**
 *
 * @author Anderson
 */
public class ContatosDAO {
    Connection conn;
    
    public ContatosDAO() {
        this.conn = new Conexao().getConnection();
    }
    
    public String save(Contato contato) throws SQLException{
        String sql = "INSERT INTO contatos "
                    +" (nome,endereco,email)" 
                    +"VALUES(?,?,?)";
        
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setString(1, contato.getNome());
        pst.setString(2, contato.getEndereco());
        pst.setString(3, contato.getEmail());
        pst.execute();
        pst.close();
        
        return "Contato inserido com sucesso!";
    }
    
    public String update(Contato contato) throws SQLException{
        String sql = "UPDATE contatos SET nome=?, endereco=?, email=? WHERE id=?";
        
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setString(1, contato.getNome());
        pst.setString(2, contato.getEndereco());
        pst.setString(3, contato.getEmail());
        pst.setInt(4, contato.getId());
        pst.execute();
        pst.close();
        
        return "Contato Atualizado com sucesso!";
    }
    
    public List<Contato> getContatosList() throws SQLException{
        String sql = "SELECT * FROM contatos";
        PreparedStatement pst = this.conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery();
        
        List<Contato> lista = new ArrayList<>();
        
        while(res.next()){
            Contato c = new Contato(
                    res.getInt("id"), 
                    res.getString("nome"), 
                    res.getString("endereco"), 
                    res.getString("email"));
            lista.add(c);
        }
        
        return lista;
    }
    
    public String delete(int id) throws SQLException{
        String sql = "DELETE FROM contatos WHERE id = ?";
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        pst.close();
        
        return "Contato excluido com sucesso!";
    }
    
    public static void main(String[] args) throws SQLException {
        Connection conn = new Conexao().getConnection();
        
        


        
        System.out.println("conexao ok");
    }
}
