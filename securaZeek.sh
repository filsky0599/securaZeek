#!/bin/bash

#Variables
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'
DIR="./Results"

#Change terminal window dimensions

resize -s 34 100
clear

#Change environment variables
export LC_NUMERIC="en_US.UTF-8"

#Create the directory that will contain all the output files.
echo -e -n "Creating the directory for the results...\n"

if ([ -d "$DIR" ])
then
    echo -e "\n${RED}Ouch!!!${NC}\n\"Results\" directory seems like to already exists. You should execute it in another directory.\nPress \"s\" to exit or press \"n\" to go ahead."
    echo -e -n "Press [s] or [n]: "
    read ENTER

case $ENTER in
    s)
        exit 1
        ;;
    n)
        ;;
    *)
        exit 1
        ;;
esac

else
    mkdir ./$DIR
    echo "Directory has been successfully created. Press enter to go ahead."
    read ENTER
fi

clear

#Print ASCII decorator
cat << "EOF"


                                                            /$$$$$$$$                     /$$      
                                                           |_____ $$                     | $$      
  /$$$$$$$  /$$$$$$   /$$$$$$$ /$$   /$$  /$$$$$$  /$$$$$$      /$$/   /$$$$$$   /$$$$$$ | $$   /$$
 /$$_____/ /$$__  $$ /$$_____/| $$  | $$ /$$__  $$|____  $$    /$$/   /$$__  $$ /$$__  $$| $$  /$$/
|  $$$$$$ | $$$$$$$$| $$      | $$  | $$| $$  \__/ /$$$$$$$   /$$/   | $$$$$$$$| $$$$$$$$| $$$$$$/ 
 \____  $$| $$_____/| $$      | $$  | $$| $$      /$$__  $$  /$$/    | $$_____/| $$_____/| $$_  $$ 
 /$$$$$$$/|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$     |  $$$$$$$ /$$$$$$$$|  $$$$$$$|  $$$$$$$| $$ \  $$
|_______/  \_______/ \_______/ \______/ |__/      \_______/|________/ \_______/ \_______/|__/  \__/
                                                                                              
                                                                                        by filsky99
EOF
   
#Print the menu                                                                                                   
echo -e "${BLUE}What do you want to do?${NC}\n"

echo -e "${RED}[1]${NC}   Obtain file info."
echo -e "${RED}[2]${NC}   Sort the connections according to their duration."
echo -e "${RED}[3]${NC}   Sort multiple connections according to their duration."
echo -e "${RED}[4]${NC}   Find the number of connections per day."
echo -e "${RED}[5]${NC}   Amount of data transmitted by connections."
echo -e "${RED}[6]${NC}   Amount of data transmitted by a specific connection."
echo -e "${RED}[7]${NC}   Analyze the payload."
echo -e "${RED}[8]${NC}   Extract the URI."
echo -e "${RED}[9]${NC}   Find out the number of hosts for each domain."
echo -e "${RED}[10]${NC}  Find out the number of DNS query types for each domain."
echo -e "${RED}[11]${NC}  Help."

echo -e -n "\nChoose an option: "
read OPTION

#Case switch menu
case $OPTION in
    1)
        echo -e -n "\nInsert the file name(<filename>.pcap): "
        read FILENAME
        capinfos $FILENAME > FileInformation.txt
        mv FileInformation.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    2)
        cat conn.log | zeek-cut id.orig_h id.resp_h duration | sort -k 3 -rn > LongestConnection.txt
        mv LongestConnection.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    3)
        cat conn.log | zeek-cut id.orig_h id.resp_h duration | sort | grep -v '-' | datamash -g 1,2 sum 3 | sort -k 3 -rn > AbsLongestConnection.txt
        mv AbsLongestConnection.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    4)
        cat conn.log | zeek-cut id.orig_h id.resp_h | sort | uniq -c | sort -rn > NumberOfConnections.txt
        mv NumberOfConnections.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    5)
        cat conn.log | zeek-cut id.orig_h id.resp_h orig_bytes | sort | uniq -c | sort -rn > AmountOfData.txt
        mv AmountOfData.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    6)
        echo -e -n "\nInsert the origin IP: "
        read ORIGINIP
        echo -e -n "\nInsert the destination IP: "
        read DESTINATIONIP
        echo -e "\n   NConn OrginIP DestIP  NByes\n"
        cat conn.log | zeek-cut id.orig_h id.resp_h orig_bytes | grep $ORIGINIP | grep $DESTINATIONIP | sort | uniq -c | sort -rn | head
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    7)  
        echo -e -n "\nInsert the file name(<filename>.pcap): "
        read FILENAME
        echo -e -n "\nInsert the origin IP: "
        read ORIGINIP
        echo -e -n "\nInsert the destination IP: "
        read DESTINATIONIP
        ngrep -q -I $FILENAME host $ORIGINIP and host $DESTINATIONIP | less > Payload.txt
        mv Payload.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    8)
        echo -e -n "\nInsert the destination IP: "
        read DESTINATIONIP
        cat http.log | zeek-cut id.orig_h id.resp_h uri | grep $DESTINATIONIP | sort | uniq -c | sort -rn > URI.txt
        mv URI.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    9)
        cat dns.log | zeek-cut query | sort | uniq | rev | cut -d . -f 1-2 | rev | sort | uniq -c | sort -rn > NumberOfHost.txt
        mv NumberOfHosts.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    10)
        echo -e -n "\nInsert the domain name: "
        read DOMAINAME
        cat dns.log | zeek-cut qtype_name query | grep $DOMAINAME | cut -f 1 | sort | uniq -c | sort -rn > RecordDNS.txt
        mv RecordDNS.txt $DIR
        echo -e "\n${RED}$OPTION${NC} ${BLUE}DONE${NC}\n"
        ;;
    11)
        echo -e "                            SECURAZEEK                 by filsky99
                            
        securaZeek is a tool based on the zeek-cut tool.
        All the output files are saved in a directory called \"Results\".
        If you have a directory with the same name, you can choose to go ahead using it.
        Otherwise you can terminate the tool and change the directory, in order to enable
        the script to create the \"Results\" directory.
        It is recommended to execute the tool in the folder 
        that contains Zeek created the <filename>.log files.\n"
        ;;
    *)
        echo -e "Invalid choice. Exiting..."
        exit 1
        ;;
esac
