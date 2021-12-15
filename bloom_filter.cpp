/*
Name: Anurag Kulkarni
Div: SY IC B
Batch: B1
Roll no.: 21
Gr No.: 11910287
*/

#include <iostream>
#include <cmath>
#define MAX 100

using namespace std;

int hash1(string name){
    int hash = 0;
    for(int i=0; i<name.size(); i++){
        //cout << name[i] << endl;
        hash = (hash + (int)name[i]) % MAX;
    }
    //cout << "Final Hash 1: " << hash << endl;
    return hash;
}

int hash2(string name){
    int hash = 3, hk;
    for(int i=0; i<name.size(); i++){
        //cout << name[i] << endl;
        hk = 23 - ((int)name[i] % 23);
        hash = (hash + hk) % MAX;
    }
    //cout << "Final Hash 2: " << hash << endl;
    return hash;
}

int hash3(string name){
    int hash = 23, hk;
    for(int i=0; i<name.size(); i++){
        hk = 71 * ((int)name[i] % 71);
        hash = (hash + hk) % MAX;
    }
    //cout << "Final Hash 3: " << hash << endl;
    return hash;
}

int hash4(string name){
    int hash = 3;
    int p = 7;
    for (int i = 0; i < name.size(); i++) {
        hash += hash * 7 + name[i] * pow(p, i);
        hash = hash % MAX;
    }
	//cout << "Final Hash 4: " << hash << endl;
    return hash;
}

void display(bool set[]){
    cout << "\nWelcome to display!" << endl;
    
    for(int i=0; i<MAX; i++){
        cout << i << "\t" << set[i] << endl;
    }
}

bool lookup(bool set[], string name){
    int one = hash1(name);
    int two = hash2(name);
    int three = hash3(name);
	int four = hash4(name);
    
    if(set[one] && set[two] && set[three] && set[four]){
        return 1;
    } else {
        return 0;
    }
}

void search(bool set[]){
    cout << "\nWelcome to search!" << endl;
    
    string name;
    
    cout << "\nEnter your name: ";
    cin >> name;
    
    if(lookup(set, name)){
        cout << "\nUsername " << name << " Found in the DataSet!" << endl;
    } else {
        cout << "\nUsername " << name << " not present in the DataSet!" << endl;
    }
}

void insert(bool set[]){
    cout << "\nWelcome to insert!" << endl;
    
    string name;
    
    cout << "\nEnter your name: ";
    cin >> name;
    
    cout << "Hello, " << name << endl;
    
    if(lookup(set, name)){
        
        cout << "\nUsername already present!" << endl;
        
    } else {
        
        int one = hash1(name);
        int two = hash2(name);
        int three = hash3(name);
		int four = hash4(name);
        
        set[one] = 1;
        set[two] = 1;
        set[three] = 1;
		set[four] = 1;
        
        cout << "\nUsername " << name << " Inserted!" << endl;
    }
}

int main()
{
    cout << "Welcome to Bloom Filter Program!" << endl;
    
    bool dataSet[MAX] = {0};
    int choice;
    
    do{
        cout << "\n1.Search\n2.Insert\n3.Display\n4.Exit" << endl;
        cout << "\nEnter your choice: ";
        cin >> choice;
        
        switch(choice){
            case 1:
                search(dataSet);
                break;
            case 2:
                insert(dataSet);
                break;
            case 3:
                display(dataSet);
                break;
        }
        
    } while(choice!=4);
    
    cout << "\nExiting Program!" << endl;
    return 0;
}