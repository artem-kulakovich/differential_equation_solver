package by.bntu.fitr.model.main_logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Parser extends Remote {
    public double parse(double x, double y) throws RemoteException;
    public void setEquation(String equation) throws RemoteException;
    public String getEquation() throws RemoteException;

}
