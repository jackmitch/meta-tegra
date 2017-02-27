SUMMARY = "NVIDIA CUDA Deep Neural Network library"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://cudnn.h;endline=48;md5=d212e7eced2562852ccf8267e4811e6f"

SRC_URI = "http://developer.download.nvidia.com/devzone/devcenter/mobile/jetpack_l4t/001/linux-x64/cudnn_v2_target.tgz;downloadfilename=${BP}.tgz"
SRC_URI[md5sum] = "42491aa82b56575ffa7e759a7be64e13"
SRC_URI[sha256sum] = "55771407126e7da86cb996b777ca269379ae14fa8ffc4e58246fbb99a19d5fd7"

DEPENDS = "dpkg-native"

COMPATIBLE_MACHINE = "(tegra124)"
PACKAGE_ARCH = "${SOC_FAMILY_PKGARCH}"

S = "${WORKDIR}/cuDNN"
B = "${WORKDIR}/build"

do_configure() {
    tar xf ${WORKDIR}/cudnn-6.5-linux-ARMv7-v2.tgz --strip-components=1 -C ${B}
    cp ${B}/cudnn.h ${S}
}
do_populate_lic[depends] += "${PN}:do_configure"

do_compile() {
    :
}

do_install() {
    install -d ${D}${includedir}
    install -d ${D}${libdir}

    install ${B}/cudnn.h ${D}${includedir}
    install ${B}/libcudnn.so.6.5.48 ${D}${libdir}
    ln -sr ${D}${libdir}/libcudnn.so.6.5.48 ${D}${libdir}/libcudnn.so.6.5
    ln -sr ${D}${libdir}/libcudnn.so.6.5 ${D}${libdir}/libcudnn.so
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

RDEPENDS_${PN} = "cuda-cudart"
