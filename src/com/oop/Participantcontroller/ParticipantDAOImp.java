package com.oop.Participantcontroller;

import com.oop.model.Participant;
import com.oop.sParticipantdb.ParticipantDb;
import java.awt.Component;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAOImp implements ParticipantDAO {

    private final Component parentComponent;

    public ParticipantDAOImp(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public ParticipantDAOImp() {
        this.parentComponent = null;   
    }
    
    @Override
    public void save(Participant students) {
        try {
            Connection con = ParticipantDb.getConnection();
            String sql = "INSERT INTO students(fname, email, contact_number) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, students.getFname());
            ps.setString(2, students.getEmail());
            ps.setString(3, students.getContact_number());          
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Saved!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void update(Participant students) {
        try {
            Connection con = ParticipantDb.getConnection();
            String sql = "UPDATE students SET fname=?, email=?, contact_number=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setString(1, students.getFname());
            ps.setString(2, students.getEmail());
            ps.setString(3, students.getContact_number());            
            ps.setInt(4, students.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Updated!");           
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }  
    }

    @Override
    public Participant get(int id) {
        Participant st = new Participant();
        try {
            Connection con = ParticipantDb.getConnection();
            String sql = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                st.setId(rs.getInt("id"));
                st.setFname(rs.getString("fname"));
                st.setEmail(rs.getString("email"));
                st.setContact_number(rs.getString("contact_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error"); 
        }
        return st;
    }

    @Override
    public List<Participant> list() {
        List<Participant> list = new ArrayList<Participant>();
        try {
            Connection con = ParticipantDb.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Participant st = new Participant();
                st.setId(rs.getInt("id"));
                st.setFname(rs.getString("fname"));
                st.setEmail(rs.getString("email"));
                st.setContact_number(rs.getString("contact_number")); 
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return list;
    }

    @Override
    public void delete(Participant students) {
        try {
            Connection con = ParticipantDb.getConnection();
            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted");

           
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}