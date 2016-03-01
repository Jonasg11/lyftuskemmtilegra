package com.ru.usty.elevator;

public class Elevator implements Runnable {
	int peopleInElevator;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			peopleInElevator = 0;
			peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
			System.out.println("svona margir í lyftuni fyrst" + peopleInElevator);
			if(ElevatorScene.elevatorsMayDie){
				return;
			}
			
			for(int i = 0; i < (6 - peopleInElevator); i++){
				
			ElevatorScene.semaphore1.release(); //signal
			System.out.println("býr til pláss í lyfut" + i);
		}
			System.out.println("herna ætti persons að fara inn");
			synchronized(this){
				try {
					System.out.println("biður numer 1");
					
					wait(ElevatorScene.VISUALIZATION_WAIT_TIME);
					System.out.println("buinn að bíða númer 1");
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			
			System.out.println("buinnn að bíða");
			if(ElevatorScene.elevatorWaitMutex.tryAcquire())
			{
				try {
					ElevatorScene.personInElevatorCountMutex.acquire();
					peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
					ElevatorScene.personInElevatorCountMutex.release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("er að fylla lyftu "+ peopleInElevator);
				for(int i = 0; i < (6 - peopleInElevator); i++){
					System.out.println("fyllir lyftur" +i);
					try {
						ElevatorScene.semaphore1.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //signal
				
			}
			}
			ElevatorScene.elevatorWaitMutex.release();
			peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
			for(int i = 0; i < peopleInElevator; i++){
				System.out.println("hleypir fólki út" +i);

				ElevatorScene.elevatorGoOutMutex.release();
			}
			synchronized(this){
				try {
					System.out.println("biður numer 2");
					
					wait(ElevatorScene.VISUALIZATION_WAIT_TIME);
					System.out.println("buinn að bíða numer 2");
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
		
	}
	
	}
	
}
