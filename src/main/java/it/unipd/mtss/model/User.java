////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {

    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;

    public User(String _name, String _surname, LocalDate _dateOfBirth, String _email){
        this.name = _name;
        this.surname = _surname;
        this.email = _email;
        
        if(_dateOfBirth == null) {
            throw new IllegalArgumentException("La data di nascita è null");
        }
        if(_dateOfBirth.isBefore(LocalDate.now())) {
            this.dateOfBirth   = _dateOfBirth;
        }else {
            throw new IllegalArgumentException("La data di nascita non può essere maggiore di quella attuale");
        }
        
    }

    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    
}