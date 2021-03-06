/* Copyright (c) 2015, Frédéric Fauberteau
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package fr.ece.fauberte.fakeroute.trace;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Generates a FakeTrace randomly.
 * 
 * @author Frédéric Fauberteau
 * @see FakeTrace
 */
public class FakeTraceGenerator {
  
  private final static int MIN_HOPS = 5;
  
  private final static int MAX_HOPS = 30;
  
  private final String host;
  
  public FakeTraceGenerator(String host) {
    this.host = host;
  }
  
  public String generate() throws UnknownHostException {
    InetAddress address = InetAddress.getByName(host);
    FakeTrace trace = new FakeTrace(host, address);
    Random rand = new Random();
    int nbHop = MIN_HOPS + rand.nextInt(MAX_HOPS - MIN_HOPS);
    HopFactory factory = new HopFactory();
    for (int i = 0; i < nbHop - 1; i++) {
      trace.addHop(factory.getRandomHop());
    }
    trace.addHop(factory.getParameterizedHop(address));
    
    return trace.toString();
  }

}
