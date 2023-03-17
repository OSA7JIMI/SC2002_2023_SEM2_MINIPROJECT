package request;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import databaseRequest.DatabaseRequestAccessor;

public class User {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("select user 1 or 2, select 3 to end");
			int userNum = sc.nextInt();
			switch(userNum) {
			case 1:
				System.out.println("1 to add to array, 2 to view array by index, 3 to view all request made by user, 4 to settle request");
				int choice = sc.nextInt();
				if(choice==1) {
					Request r = new RequestChangeTitle("test1");
					DatabaseRequestAccessor.addToArray(r);
					continue;
				}else if(choice==2) {
					System.out.println("enter index");
					int index = sc.nextInt();
					Request temp = RequestChangeTitle.viewRequestByIndex(index);
					System.out.println(temp.Title);
					continue;
				}else if(choice==3) {
					List<Request> tempList = DatabaseRequestAccessor.requestArray.stream().filter(r -> r.Title == "test1").collect(Collectors.toList());
					System.out.println("All request made by user 1");
					for(int i=0; i<tempList.size(); i++) {
						System.out.println(tempList.get(i).Title + " pending "+tempList.get(i).pending);
					}
					continue;
				}else if(choice==4) {
					System.out.println("List of pending requests:");
					List<Request> tempList = DatabaseRequestAccessor.requestArray.stream().filter(r -> r.Title == "test1" && r.pending == true).collect(Collectors.toList());
					ArrayList<Integer> tempList2 = new ArrayList<Integer>();
					for(int a=0; a<tempList.size(); a++) {
						tempList2.add(DatabaseRequestAccessor.requestArray.indexOf(tempList.get(a)));
					}
					for(int a=0; a<tempList.size(); a++) {
						System.out.println("title is "+tempList.get(a).Title+" pending "+tempList.get(a).pending);
					}
					System.out.println("Select request to settle");
					int index = sc.nextInt();
					System.out.println("0 for deny, 1 for approve");
					int approve = sc.nextInt();
					Request r = DatabaseRequestAccessor.requestArray.get(tempList2.get(index));
					if(approve==0) {
						r.pending = false;
						r.approval = false;
					}else {
						r.pending = false;
						r.approval = true;
					}
					continue;
				}
				break;
			case 2:
				System.out.println("1 to add to array, 2 to view array by index, 3 to view all request made by user, 4 to settle request");
				int choice1 = sc.nextInt();
				if(choice1==1) {
					Request r = new RequestChangeTitle("test2");
					DatabaseRequestAccessor.addToArray(r);
					continue;
				}else if(choice1==2) {
					System.out.println("enter index");
					int index = sc.nextInt();
					Request temp = RequestChangeTitle.viewRequestByIndex(index);
					System.out.println(temp.Title);
					continue;
				}else if(choice1==3) {
					List<Request> tempList = DatabaseRequestAccessor.requestArray.stream().filter(r -> r.Title == "test2").collect(Collectors.toList());
					System.out.println("All request made by user 2");
					for(int i=0; i<tempList.size(); i++) {
						System.out.println(tempList.get(i).Title+" pending "+tempList.get(i).pending);
					}
					continue;
				}else if(choice1==4) {
					System.out.println("List of pending requests:");
					List<Request> tempList = DatabaseRequestAccessor.requestArray.stream().filter(r -> r.Title == "test2" && r.pending == true).collect(Collectors.toList());
					ArrayList<Integer> tempList2 = new ArrayList<Integer>();
					for(int a=0; a<tempList.size(); a++) {
						tempList2.add(DatabaseRequestAccessor.requestArray.indexOf(tempList.get(a)));
					}
					for(int a=0; a<tempList.size(); a++) {
						System.out.println("title is "+tempList.get(a).Title+" pending "+tempList.get(a).pending);
					}
					System.out.println("Select request to settle");
					int index = sc.nextInt();
					System.out.println("0 for deny, 1 for approve");
					int approve = sc.nextInt();
					Request r = DatabaseRequestAccessor.requestArray.get(tempList2.get(index));
					if(approve==0) {
						r.pending = false;
						r.approval = false;
					}else {
						r.pending = false;
						r.approval = true;
					}
					continue;
				}
				break;
			case 3:
				return;
			}
		}
	}

}
