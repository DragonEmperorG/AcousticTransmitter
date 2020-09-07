/* Copyright 2019 UNL.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Derived from jffpack, by lqian1995@qq.com.
 *
 * jfftpack is a Java version of fftpack. jfftpack is based
 * on Paul N. Swarztraubre's Fortran code and Pekka Janhuen's
 * C code. It is developed as part of my official duties as
 * lead software engineer for SCUBA-2 FTS projects
 * (www.roe.ac.uk/ukatc/projects/scubatwo/)
 *
 * The original fftpack was public domain, so jfftpack is public domain too.
 * @author Baoshe Zhang
 * @author Astronomical Instrument Group of University of Lethbridge.
 */

package cn.edu.whu.unsc.audio.transmitter.utils.fftpack;

public class Complex1D {
    /**
     * <em>x</em>[<em>i</em>] is the real part of <em>i</em>-th complex data.
     */
    public double x[];
    /**
     * <em>y</em>[<em>i</em>] is the imaginary part of <em>i</em>-th complex data.
     */
    public double y[];
}
