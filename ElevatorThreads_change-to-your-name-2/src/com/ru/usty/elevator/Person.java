package com.ru.usty.elevator;

public class Person implements Runnable{
	int sourceFloor, destinationFloor;
	public Person(int sourceFloor, int destinationFloor){
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
	}
	@Override
	public void run() {
		System.out.println("Persona til");
			try {
				ElevatorScene.elevatorWaitMutex.acquire();
				System.out.println("nadi fyrstu semaphore");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			try {
				ElevatorScene.semaphore1.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ElevatorScene.elevatorWaitMutex.release();
				ElevatorScene.scene.incrementNumberOfPeopleInElevator(1);
				ElevatorScene.scene.incrementNumberOfPeopleGoingOutFloor(destinationFloor);
				ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
				
					
					
					try {
						ElevatorScene.elevatorGoOut.acquire();
						ElevatorScene.scene.decrementNumberOfPeopleInElevator(1);
						ElevatorScene.scene.decrementNumberOfPeopleGoingOutFloor(destinationFloor);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("farinn út");
					
		
		

	}
}