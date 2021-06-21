package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.print("Check-in date (DD/MM/YYYY): ");
			Date checkIn = sdf.parse(sc.next()); // INSERIR UMA NOVA DATA
			System.out.print("Check-out date (DD/MM/YYYY: ");
			Date checkOut = sdf.parse(sc.next()); // INSERIR UMA NOVA DATA
			
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (DD/MM/YYYY): ");
			checkIn = sdf.parse(sc.next()); // INSERIR UMA NOVA DATA
			System.out.print("Check-out date (DD/MM/YYYY: ");
			checkOut = sdf.parse(sc.next()); // INSERIR UMA NOVA DATA
						
			reservation.updateDates(checkIn, checkOut); //MÉTODO PARA ATUALIZAR AS DATAS
			System.out.println("Reservation: " + reservation);			
		}
		catch (ParseException e) {
			System.out.println("Invalid date format");
		}
		catch (DomainException e) {
			System.out.println("Error in reservation" + e.getMessage()); // CHAMA A MENSAGEM DEFINIDA NA CLASSE RESERVATION PARA ESSA EXCEÇÃO
		}
		catch (RuntimeException e) { // 
			System.out.println("Unexpected error");
		}
		
		sc.close();
	}
}
