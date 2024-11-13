package it.unibo.deathnote.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{
    private final List<Death> deathList;
    long startTime;
    long elapsedTime = 0L;
    
    public DeathNoteImplementation(){
        deathList = new ArrayList<>();
    }

    private class Death{
        private final String name;
        private String cause;
        private String details;

        public Death(String name){
            this.name = name;
        }

        public void setCause(String cause){
            this.cause = cause;
        }
        public void setDetails(String details){
            this.details = details;
        }

        public String getName(){
            return this.name;
        }
        public String getCause(){
            return this.cause;
        }
        public String getDetails(){
            return this.details;
        }
    }

    @Override
    public String getRule(int ruleNumber){
        if (ruleNumber > 0 && ruleNumber <= RULES.size()) {
            return RULES.get(ruleNumber);
        }else{
            throw new IllegalArgumentException("Numero della regola inesistente");
        }
    }

    @Override
    public void writeName(String name){
        if(name != null && !name.isBlank()){
            startTime = System.currentTimeMillis();
            this.deathList.add(new Death(name));
        }else{
            throw new NullPointerException("The name shouldn't be null");
        }
    }

    @Override
    public boolean writeDeathCause(String cause){
        elapsedTime = (new Date()).getTime() - startTime;
        if(!this.deathList.isEmpty() && cause != null && elapsedTime <= 40){
            this.deathList.getLast().setCause(cause);
            return true;
        }else{
            throw new IllegalStateException("The cause should be not null and written within 40ms");
        }
    }

    @Override
    public boolean writeDetails(String details){
        elapsedTime = (new Date()).getTime() - startTime;
        if(!this.deathList.isEmpty() && details != null && elapsedTime <= 1400){
            this.deathList.getLast().setDetails(details);
            return true;
        }else{
            throw new IllegalStateException("The detail should be not null and written within 6 second and 40ms");
        }
    }

    @Override
    public String getDeathCause(String name){
        for (Death death : this.deathList) {
            if(death.getName().equals(name)){
                return death.getCause() != null ? death.getCause() : "";
            }
        }
        throw new IllegalArgumentException("Death cause not in list");
    }

    @Override
    public String getDeathDetails(String name){
        for (Death death : this.deathList) {
            if(death.getName().equals(name)){
                return death.getDetails() != null ? death.getDetails() : "";
            }
        }
        throw new IllegalArgumentException("Death details not in list");
    }

    @Override
    public boolean isNameWritten(String name){
        for (Death death : this.deathList) {
            if(death.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}