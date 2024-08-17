package com.oop.Participantcontroller;

import com.oop.model.Participant;
import java.util.List;


public interface ParticipantDAO {
    public void save(Participant students);
    public void update(Participant students);
    public void delete(Participant students);
    public Participant get(int id);
    public List<Participant> list();
}
