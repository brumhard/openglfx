package com.huskerdev.openglfx.internal.d3d9

import com.huskerdev.openglfx.GLExecutor

internal const val WGL_ACCESS_WRITE_DISCARD_NV = 0x2

fun isNVDXInteropSupported() = NVDXInterop.hasNVDXInteropFunctions()

internal class NVDXInterop {
    companion object {
        @JvmStatic external fun hasNVDXInteropFunctions(): Boolean

        @JvmStatic external fun wglDXOpenDeviceNV(dxDevice: Long): Long
        @JvmStatic external fun wglDXCloseDeviceNV(hDevice: Long): Boolean
        @JvmStatic external fun wglDXRegisterObjectNV(device: Long, dxResource: Long, name: Int, type: Int, access: Int): Long
        @JvmStatic external fun wglDXSetResourceShareHandleNV(dxResource: Long, shareHandle: Long): Boolean
        @JvmStatic external fun wglDXUnregisterObjectNV(device: Long, obj: Long): Boolean
        @JvmStatic external fun wglDXLockObjectsNV(interopHandle: Long, sharedTextureHandle: Long): Boolean
        @JvmStatic external fun wglDXUnlockObjectsNV(interopHandle: Long, sharedTextureHandle: Long): Boolean

        @JvmStatic external fun createD3DTexture(device: Long, width: Int, height: Int): LongArray
        @JvmStatic external fun replaceD3DTextureInResource(resource: Long, newTexture: Long)

        init {
            GLExecutor.loadBasicFunctionPointers()
        }

        val interopHandle by lazy {
            wglDXOpenDeviceNV(D3D9Device.fxInstance.handle)
        }
    }
}