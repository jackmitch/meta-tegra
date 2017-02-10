PACKAGECONFIG_append_tegra210 = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'egl glesv2', '', d)}"
PACKAGE_ARCH_tegra210 = "${SOC_FAMILY_PKGARCH}"
