package data;

import java.sql.Timestamp;

import data.DataRepository;

public class EventBuilder {
    //TODO: zmienić to po testach na private
    private int id;
    private String title;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;


    public EventBuilder setId(int id) {

        this.id = id;
        return this;
    }
    public EventBuilder setId(String idValue) {
        //TODO: nad tym trzeba popracowac bo funkcja zle zadziala kiedy posortujemy dane i bedziemy chcieli wziac ostatnie ID
        //
        //Bezpieczniej moze byc poprostu branie tablicy ale to do zmiany po konsultacjach

        if(idValue.isBlank()) {
            //            idValue=Integer.toString((int)Math.random()*100);
             this.id= DataRepository.getLastEventID()+1;
        }
        else this.id = Integer.parseInt(idValue);
        return this;
    }

    public EventBuilder setTitle(String title) {
        if(title.isBlank())
            title="Default Title";
        this.title = title;
        return this;
    }

    public EventBuilder setDescription(String description) {
        if(description.isBlank())
            description="Default Description";
        this.description = description;
        return this;
    }

    public EventBuilder setStartDate(Timestamp startDate) {

        this.startDate = startDate;
        return this;
    }

    public EventBuilder setEndDate(Timestamp endDate) {

        this.endDate = endDate;
        return this;
    }

    public Event createEvent() {
        return new Event(id, title, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Wydarzenie{" +
                "id=" + id +
                ", Tyt='" + title + '\'' +
                ", Des='" + description + '\'' +
                ", start=" + startDate +
                ", end=" + endDate +
                '}';
    }
}