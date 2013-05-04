/*
 * A simple programme for scanning ports
 * GitHub    - https://github.com/anjanborah/Port_scanner_in_Java_simple
 * Author    - Anjan Borah
 * Copyright ( c ) 2013 Anjan Borah
 */

import java.util.Scanner;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class Main {
  public static void main( String args[] ) {
    Port_Scanner object = new Port_Scanner();
  }
}

class Port_Scanner {
  
  public Port_Scanner() {
    
    try {
      System.out.print( "\tHost - " );
      String host = this.get.nextLine();
      this.check_is_IP_address( host );
      
      if( this.is_IP_address == true ) {
        String host_split[] = host.split( "\\." );
        byte address[] = {
          ( byte )Integer.parseInt( host_split[0] ),
          ( byte )Integer.parseInt( host_split[1] ),
          ( byte )Integer.parseInt( host_split[2] ),
          ( byte )Integer.parseInt( host_split[3] )
        };
        this.host_inet_address = InetAddress.getByAddress( address );
      } else {
        this.host_inet_address = InetAddress.getByName( host );
      }
      
      this.display_host_information();
      
      System.out.print( "\tStart port number - " );
      int start_point = this.get.nextInt();
      System.out.print( "\tEnd port number   - " );
      int end_point = this.get.nextInt();
      
      this.scan( start_point, end_point );
      System.out.println();
    }
    catch( Exception exception ) {
      System.out.println( "Exception - " + exception );
      System.exit( 0 );
    }
    
  }
  
  private void check_is_IP_address( String host ) throws Exception {
    String host_split[] = host.split( "\\." );
    if( host_split.length == 4 ) {
      for( int i=0; i<host_split.length; i++ ) {
        try {
          int range = Integer.parseInt( host_split[i] );
          if( range >=0 && range <= 255 ) {
            this.is_IP_address = true;
          } else {
            this.is_IP_address = false;
            break;
          }
        } catch( Exception exception ) {
          this.is_IP_address = false;
          break;
        }
      }
    } else {
      this.is_IP_address = false;
    }
  }
  
  private void display_host_information() throws Exception {
    System.out.println( "\n\t| Host name           - " + this.host_inet_address.getHostName() );
    System.out.println( "\t| Canonical host name - " + this.host_inet_address.getCanonicalHostName() );
    System.out.println( "\t| Host address        - " + this.host_inet_address.getHostAddress() + "\n" );
    this.IP_address = this.host_inet_address.getHostAddress().toString();
  }
  
  private void scan( int start_point, int end_point ) {
    System.out.println( "\tStarting the scanner...\n" );
    for( int i=start_point; i<=end_point; i++ ) {
      try {
        SocketAddress address = new InetSocketAddress( this.IP_address, i );
        Socket scan_socket = new Socket();
        scan_socket.connect( address, 2000 );
        System.out.println( "\r\tPort open  -----------" + i );
        scan_socket.close();
      } catch( Exception exception ) {
        System.out.print( "\r\tPort close -----------" + i );
      }
    }
  }
  
  private Scanner get = new Scanner( System.in );
  private boolean is_IP_address;
  private InetAddress host_inet_address;
  private String IP_address;
}
